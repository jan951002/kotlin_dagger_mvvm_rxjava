package co.kubo.dagger_kotlin_application.data.api

import co.kubo.dagger_kotlin_application.data.model.Dog
import io.reactivex.Single
import retrofit2.http.GET

interface DogApi {

    @GET("dog")
    fun getDogs(): Single<List<Dog>>
}