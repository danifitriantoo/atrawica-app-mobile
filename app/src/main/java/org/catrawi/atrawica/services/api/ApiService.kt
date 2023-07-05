package org.catrawi.atrawica.services.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.models.BookingMeta
import org.catrawi.atrawica.models.BookingMetaList
import org.catrawi.atrawica.models.BookingMetaResponseList
import org.catrawi.atrawica.models.Departure
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.models.DetailBookingMeta
import org.catrawi.atrawica.models.DetailBookingMetaList
import org.catrawi.atrawica.models.Favorite
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.models.MetaFavorite
import org.catrawi.atrawica.models.MetaRegister
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.models.TicketMeta
import org.catrawi.atrawica.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    /* Endpoint -  Authorize and Authentication */
    @GET("user/Authentication")
    fun login(
        @Query("email") email: String,
        @Query("keypass") keypass: String
    ): Call<Meta>

    @POST("user/Register")
    fun register(
        @Body data: User
    ): Call<MetaRegister>


    /* Endpoint - Place */
    @GET("Place")
    fun getAllPlace(): Call<PlaceMeta>

    @POST("Place")
    fun postPlace(
        @Body data:Place
    ): Call<PlaceMeta>

    @GET("Place")
    fun getPlace(
        @Query("Id") Id: Int
    ): Call<PlaceMeta>


    /* Endpoint - Booking */
    @GET("Booking")
    fun getAllBooking(
        @Query("userId") userId: Int
    ): Call<BookingMetaResponseList>

    @POST("Booking")
    fun postBooking(
        @Body data:Booking
    ): Call<BookingMeta>

    @GET("Booking")
    fun getBooking(
        @Query("Id") Id: Int
    ): Call<BookingMeta>


    /* Endpoint - Detail Booking */
    @GET("DetailBooking")
    fun getDetailBooking(
        @Query("bookingId") bookingId: Int
    ): Call<DetailBookingMetaList>

    @POST("DetailBooking")
    fun postDetailBooking(
        @Body data:DetailBooking
    ): Call<DetailBookingMeta>

    @PUT("Detailbooking")
    fun putDetailBooking(
        @Query("bookingId") bookingId: Int
    ): Call<DetailBookingMeta>


    /* Endpoint - Favorite */
    @POST("Favorite")
    fun postFavorite(
        @Body data:Favorite
    ): Call<MetaFavorite>


    /* Endpoint - Ticket */
    @GET("Ticket")
    fun getTicket(
        @Query("PlaceId") placeId: Int,
        @Query("DepartureId") departureId: Int
    ): Call<TicketMeta>



    companion object {
        var apiService: ApiService? = null

        private val client = OkHttpClient.Builder().apply {
            addInterceptor(TokenInterceptor())
        }.build()

        fun getService(): ApiService {

            if(apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiSetup.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService!!

        }
    }
}