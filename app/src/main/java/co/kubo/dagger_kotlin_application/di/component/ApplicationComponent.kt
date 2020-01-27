package co.kubo.dagger_kotlin_application.di.component

import android.app.Application
import co.kubo.dagger_kotlin_application.base.BaseApplication
import co.kubo.dagger_kotlin_application.di.module.ActivityBindingModule
import co.kubo.dagger_kotlin_application.di.module.ApiModule
import co.kubo.dagger_kotlin_application.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [ContextModule::class, ApiModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: BaseApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): ApplicationComponent?
    }

}