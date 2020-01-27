package co.kubo.dagger_kotlin_application.ui.dogs

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


class DogsViewModel @Inject constructor(private val dogApi: DogApi) : ViewModel() {

    private var disposable: CompositeDisposable?

    private val dogs = MutableLiveData<List<Dog>>()
    private val dogsLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    init {
        this.disposable = CompositeDisposable()
        fetchDogs()
    }

    fun getDogs(): LiveData<List<Dog>> {
        return dogs
    }

    fun getError(): LiveData<Boolean> {
        return dogsLoadError
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    private fun fetchDogs() {
        loading.value = true
        disposable!!.add(
            dogApi.getDogs().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Dog>>() {
                    override fun onSuccess(value: List<Dog>) {
                        dogsLoadError.value = false
                        dogs.value = value
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
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