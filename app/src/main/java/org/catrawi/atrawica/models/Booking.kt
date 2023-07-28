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

data class BookingTicket(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: ArrayList<Booking>,
)

data class BookingMetaResponseList(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: ArrayList<BookingResponse>,
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

data class BookingResponse(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("user")
    var user: User,

    @SerializedName("place")
    var place: Place,

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

data class DetailBookingTicketMeta(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: String,

    @SerializedName("data")
    var data: ArrayList<DetailBookingTicket>
)

data class DetailBookingMetaList(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: String,

    @SerializedName("data")
    var data: ArrayList<DetailBooking>
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

    @SerializedName("price")
    var price: Int,
)

data class DetailBookingTicket(
    @SerializedName("id")
    var id: Int,

    @SerializedName("bookingId")
    var bookingId: Int,

    @SerializedName("ticket")
    var ticket: TicketRawResponse,

    @SerializedName("paymentId")
    var paymentMethod: Int,

    @SerializedName("departureTime")
    var departureTime: String,

    @SerializedName("backTime")
    var backTime: String,

    @SerializedName("status")
    var status: Boolean,

    @SerializedName("price")
    var price: Int,
)