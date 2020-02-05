package co.kubo.dagger_kotlin_application.di.module

import co.kubo.dagger_kotlin_application.ui.dogs.DogsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideDogsFragment(): DogsFragment

}