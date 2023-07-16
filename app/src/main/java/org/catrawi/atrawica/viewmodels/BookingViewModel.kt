package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.models.BookingMeta
import org.catrawi.atrawica.models.BookingMetaList
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.viewmodels.repository.BookingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import kotlin.math.log

class BookingViewModel(
    private val repository: BookingRepository
) : ViewModel() {

    val responseData = MutableLiveData<Booking>()
    val responseList = MutableLiveData<ArrayList<Booking>>()
    val errorLog = MutableLiveData<String>()

//    fun getAllBooking() {

//        val response = repository.getAllBooking(18)
//        /* buat inisiasi lain untuk get dan put  */
//
//        response.enqueue(object: Callback<BookingMetaList> {
//            override fun onResponse(call: Call<BookingMetaList>, response: Response<BookingMetaList>) {
//
//               when (response.code()) {
//                   200 -> responseList.postValue(response.body()?.data)
//                   400 -> errorLog.postValue("Not Found")
//                   404 -> errorLog.postValue("Server Value")
//               }
//
//                Log.d("Status Code", "code: ${response.code()}")
//            }
//
//            override fun onFailure(call: Call<BookingMetaList>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//
//        /* lanjutkan buat controller api call method get dan put untuk endpoint booking */
//    }

        fun postBooking(token:String,data: Booking) {

            /* inisiasi untuk action repository */
            val response = repository.postBooking(token,data)

            /* controller untuk api call method post untuk endpoint booking */
            response.enqueue(object : Callback<BookingMeta> {
                override fun onResponse(call: Call<BookingMeta>, response: Response<BookingMeta>) {

                    when (response.code()) {
                        200 -> responseData.postValue(response.body()?.data)
                        401 -> errorLog.postValue("Not Found")
                        404 -> errorLog.postValue("Server Error")
                    }

                    Log.d("Status Code", "code : ${response.code()}")
                    Log.d("Response Body", "response : ${response.body()}")

                }

                override fun onFailure(call: Call<BookingMeta>, t: Throwable) {
                    errorLog.postValue(t.message)
                    Log.d("Error", errorLog.toString())
                }

            })

        }


}
