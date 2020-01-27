package co.kubo.dagger_kotlin_application.di.module

import co.kubo.dagger_kotlin_application.data.api.DogApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class ApiModule {

    @Module
    companion object {

        private const val BASE_URL = "http://64.225.120.248/api/"

        @Singleton
        @Provides
        @JvmStatic
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideDogApi(retrofit: Retrofit): DogApi {
            return retrofit.create(DogApi::class.java)
        }
    }

}