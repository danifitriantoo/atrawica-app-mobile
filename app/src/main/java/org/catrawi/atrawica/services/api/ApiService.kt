package org.catrawi.atrawica.services.api

import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.models.BookingMeta
import org.catrawi.atrawica.models.BookingMetaResponseList
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.models.DetailBookingMeta
import org.catrawi.atrawica.models.DetailBookingMetaList
import org.catrawi.atrawica.models.DetailBookingTicket
import org.catrawi.atrawica.models.DetailBookingTicketMeta
import org.catrawi.atrawica.models.Favorite
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.models.MetaFavorite
import org.catrawi.atrawica.models.MetaRegister
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.models.TicketMeta
import org.catrawi.atrawica.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /* Endpoint -  Authorize and Authentication */
    @GET(ApiSetup.LOGIN_ENDPOINT)
    fun login(
        @Query("email") email: String,
        @Query("keypass") keypass: String
    ): Call<Meta>

    @POST(ApiSetup.REGISTER_ENDPOINT)
    fun register(
        @Body data: User
    ): Call<MetaRegister>

    @PUT(ApiSetup.REGISTER_ENDPOINT)
    fun updateBudget(
        @Header("Authorization") token:String,
        @Body data: User
    ): Call<MetaRegister>


    /* Endpoint - Place */
    @GET(ApiSetup.PLACE_ENDPOINT)
    fun getAllPlace(
        @Header("Authorization") token:String,
    ): Call<PlaceMeta>


    /* Endpoint - Booking */
    @GET(ApiSetup.BOOKING_ENDPOINT + "/{UserId}")
    fun getAllBooking(
        @Header("Authorization") token:String,
        @Path("UserId") userId: Int
    ): Call<BookingMetaResponseList>

    @POST(ApiSetup.BOOKING_ENDPOINT)
    fun postBooking(
        @Header("Authorization") token:String,
        @Body data:Booking
    ): Call<BookingMeta>

    /* Endpoint - Detail Booking */
    @GET(ApiSetup.DETAIL_BOOKING_ENDPOINT + "/{BookingId}")
    fun getDetailBooking(
        @Header("Authorization") token:String,
        @Path("BookingId") bookingId: Int
    ): Call<DetailBookingMetaList>

    @GET(ApiSetup.DETAIL_BOOKING_ENDPOINT + "/{BookingId}")
    fun getDetailBookingTicket(
        @Header("Authorization") token:String,
        @Path("BookingId") bookingId: Int
    ): Call<DetailBookingTicketMeta>

    @POST(ApiSetup.DETAIL_BOOKING_ENDPOINT)
    fun postDetailBooking(
        @Header("Authorization") token:String,
        @Body data:DetailBooking
    ): Call<DetailBookingMeta>

    @PUT(ApiSetup.DETAIL_BOOKING_ENDPOINT)
    fun putDetailBooking(
        @Header("Authorization") token:String,
        @Query("bookingId") bookingId: Int
    ): Call<DetailBookingMeta>

    /* Endpoint - Ticket */
    @GET(ApiSetup.TICKET_ENDPOINT)
    fun getTicket(
        @Header("Authorization") token:String,
        @Query("PlaceId") placeId: Int,
        @Query("DepartureId") departureId: Int
    ): Call<TicketMeta>


    companion object {
        var apiService: ApiService? = null

        fun getService(): ApiService {

            if(apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiSetup.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService!!

        }
    }
}