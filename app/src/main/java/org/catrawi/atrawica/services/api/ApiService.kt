package org.catrawi.atrawica.services.api

import okhttp3.OkHttpClient
import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.models.BookingMeta
import org.catrawi.atrawica.models.BookingMetaList
import org.catrawi.atrawica.models.Departure
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.models.DetailBookingMeta
import org.catrawi.atrawica.models.Favorite
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.models.MetaFavorite
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.models.TicketMeta
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
    fun login(@Query("email") email: String, @Query("keypass") keypass: String): Call<Meta>

    @POST("user/Register")
    suspend fun register(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("keypass") keypass: String,
        @Query("noHp") no_hp: String,
    ): Call<String>


    /* Endpoint - Place */
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImRhbmkiLCJOb19IcCI6InN0cmluZyIsIkJ1ZGdldCI6IjAiLCJlbWFpbCI6ImZuQG1haWwuY29tIiwibmJmIjoxNjg4NTE4NDA0LCJleHAiOjE2ODg1MjIwMDQsImlhdCI6MTY4ODUxODQwNH0.nG272sevGLlM4c8ZEWAuBIamgBDv4UzBo0yN-yJhsBY")
    @GET("Place")
    fun getAllPlace(): Call<PlaceMeta>

    @POST("Place")
    fun postPlace(@Body data:Place): Call<PlaceMeta>

    @GET("Place")
    fun getPlace(@Query("Id") Id: Int): Call<PlaceMeta>


    /* Endpoint - Booking */
    @GET("Booking")
    fun getAllBooking(@Query("userId") userId: Int): Call<BookingMetaList>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImRhbmkiLCJOb19IcCI6InN0cmluZyIsIkJ1ZGdldCI6IjAiLCJlbWFpbCI6ImZuQG1haWwuY29tIiwibmJmIjoxNjg4NTE4NDA0LCJleHAiOjE2ODg1MjIwMDQsImlhdCI6MTY4ODUxODQwNH0.nG272sevGLlM4c8ZEWAuBIamgBDv4UzBo0yN-yJhsBY")
    @POST("Booking")
    fun postBooking(@Body data:Booking): Call<BookingMeta>

    @GET("Booking")
    fun getBooking(@Query("Id") Id: Int): Call<BookingMeta>


    /* Endpoint - Detail Booking */
    @GET("DetailBooking")
    fun getDetailBooking(@Query("bookingId") bookingId: Int): Call<DetailBookingMeta>

    @POST("DetailBooking")
    fun postDetailBooking(@Body data:DetailBooking): Call<DetailBookingMeta>

    @PUT("Detailbooking")
    fun putDetailBooking(@Query("bookingId") bookingId: Int): Call<DetailBookingMeta>


    /* Endpoint - Favorite */
    @POST("Favorite")
    fun postFavorite(@Body data:Favorite): Call<MetaFavorite>


    /* Endpoint - Ticket */
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImRhbmkiLCJOb19IcCI6InN0cmluZyIsIkJ1ZGdldCI6IjAiLCJlbWFpbCI6ImZuQG1haWwuY29tIiwibmJmIjoxNjg4NTE4NDA0LCJleHAiOjE2ODg1MjIwMDQsImlhdCI6MTY4ODUxODQwNH0.nG272sevGLlM4c8ZEWAuBIamgBDv4UzBo0yN-yJhsBY")
    @GET("Ticket")
    fun getTicket(@Query("PlaceId") placeId: Int,
                  @Query("DepartureId") departureId: Int): Call<TicketMeta>



    companion object {
        var apiService: ApiService? = null

        fun getService(): ApiService {

            if(apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiSetup.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(okHttpClient())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService!!

        }

        private fun okHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
//                .addInterceptor(TokenInterceptor())
                .build()
        }
    }
}