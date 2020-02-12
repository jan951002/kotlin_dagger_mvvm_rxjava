package co.kubo.dagger_kotlin_application.ui.createDog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kubo.dagger_kotlin_application.data.api.DogApi
import co.kubo.dagger_kotlin_application.data.model.Dog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateDogViewModel @Inject constructor(private val dogApi: DogApi) : ViewModel() {

    private var disposable: CompositeDisposable? = CompositeDisposable()

    private val createdDog = MutableLiveData<Dog>()
    private val createDogError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()


    fun getDogs(): LiveData<Dog> {
        return createdDog
    }

    fun getError(): LiveData<Boolean> {
        return createDogError
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    fun createDog(name: String, year: String) {
        loading.value = true
        disposable?.add(
            dogApi.createDog(name, year).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Dog>() {
                    override fun onSuccess(t: Dog) {
                        createDogError.value = false
                        createdDog.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        createDogError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable!!.clear()
            disposable = null
        }
    }
}