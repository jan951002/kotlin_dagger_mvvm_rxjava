package co.kubo.dagger_kotlin_application.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Dog {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String = ""
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String = ""
    @SerializedName("created_at")
    @Expose
    var createdAt: String = ""
    @SerializedName("years")
    @Expose
    var years: String = ""
}