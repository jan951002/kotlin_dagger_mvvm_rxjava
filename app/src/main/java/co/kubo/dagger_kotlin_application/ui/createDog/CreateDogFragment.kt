package co.kubo.dagger_kotlin_application.ui.createDog

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import butterknife.BindView
import co.kubo.dagger_kotlin_application.R
import co.kubo.dagger_kotlin_application.base.BaseFragment
import co.kubo.dagger_kotlin_application.factory.ViewModelFactory
import javax.inject.Inject

class CreateDogFragment : BaseFragment() {

    @BindView(R.id.btnCreate)
    lateinit var btnCreate: Button

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    private lateinit var createDogViewModel: CreateDogViewModel

    override fun layoutRes(): Int {
        return R.layout.fragment_create_dog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createDogViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CreateDogViewModel::class.java)
    }

}
