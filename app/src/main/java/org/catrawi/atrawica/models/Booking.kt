package org.catrawi.atrawica.models

import com.google.gson.annotations.SerializedName

data class BookingMeta(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: Booking,
)

data class BookingMetaList(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: ArrayList<Booking>,
)

data class Booking(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("userId")
    var userId: Int,

    @SerializedName("placeId")
    var placeId: Int,

    @SerializedName("date")
    var date: String,

    @SerializedName("qty")
    var qty: Int,
)

/* buat class data untuk detail booking */

data class DetailBookingMeta(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: String,

    @SerializedName("data")
    var data: DetailBooking
)

data class DetailBooking(
    @SerializedName("id")
    var id: Int,

    @SerializedName("bookingId")
    var bookingId: Int,

    @SerializedName("ticketId")
    var ticketId: Int,

    @SerializedName("paymentId")
    var paymentMethod: Int,

    @SerializedName("departureTime")
    var departureTime: String,

    @SerializedName("backTime")
    var backTime: String,

    @SerializedName("status")
    var status: Boolean,

    @SerializedName("totalPrice")
    var totalPrice: Int,
)