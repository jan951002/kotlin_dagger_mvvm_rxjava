package co.kubo.dagger_kotlin_application.di.module

import co.kubo.dagger_kotlin_application.ui.main.MainActivity
import co.kubo.dagger_kotlin_application.ui.main.MainFragmentBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}