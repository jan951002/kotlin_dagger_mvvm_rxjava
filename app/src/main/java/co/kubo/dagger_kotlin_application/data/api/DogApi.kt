package co.kubo.dagger_kotlin_application.data.api

import co.kubo.dagger_kotlin_application.data.model.Dog
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DogApi {

    @GET("dog")
    fun getDogs(): Single<List<Dog>>

    @FormUrlEncoded
    @POST("dog")
    fun createDog(
        @Field("name") name: String,
        @Field("years") years: String
    ): Single<Dog>
}