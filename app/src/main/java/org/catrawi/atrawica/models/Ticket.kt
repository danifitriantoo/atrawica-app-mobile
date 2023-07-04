package org.catrawi.atrawica.models

import com.google.gson.annotations.SerializedName

data class TicketMeta(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: ArrayList<Ticket>,
)

data class Ticket(
    @SerializedName("id")
    var id: Int,

    @SerializedName("place")
    var place: Place,

    @SerializedName("departure")
    var departure: Departure,

    @SerializedName("transit")
    var transit: Transit,

    @SerializedName("price")
    var price: Int
)

data class Departure(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name:String,

    @SerializedName("terminal")
    var terminal:String,
)

data class Transit(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name:String,
)