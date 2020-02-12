package co.kubo.dagger_kotlin_application.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.kubo.dagger_kotlin_application.factory.ViewModelFactory
import co.kubo.dagger_kotlin_application.ui.createDog.CreateDogViewModel
import co.kubo.dagger_kotlin_application.ui.dogs.DogsViewModel
import co.kubo.dagger_kotlin_application.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DogsViewModel::class)
    abstract fun bindDogstViewModel(dogsViewModel: DogsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(CreateDogViewModel::class)
    abstract fun bindCreateDogViewModel(createDogViewModel: CreateDogViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}