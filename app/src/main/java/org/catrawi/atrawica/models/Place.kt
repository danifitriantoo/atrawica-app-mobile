package org.catrawi.atrawica.models

import com.google.gson.annotations.SerializedName

data class PlaceMeta(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: ArrayList<Place>,
)

data class Place(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("price")
    var price: Int,

    @SerializedName("image")
    var image: String
)