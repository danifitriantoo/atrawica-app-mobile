package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.models.BookingMetaList
import org.catrawi.atrawica.models.BookingMetaResponseList
import org.catrawi.atrawica.models.BookingResponse
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.models.DetailBookingMetaList
import org.catrawi.atrawica.viewmodels.repository.HistoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel (
    private val  repository: HistoryRepository
) : ViewModel() {

    val responseData = MutableLiveData<ArrayList<DetailBooking>>()
    val responseBooking = MutableLiveData<ArrayList<BookingResponse>>()
    val errorLog = MutableLiveData<String>()

    fun getAllBooking(token:String,userId: Int) {

        val response = repository.getAllBooking(token,userId)

        response.enqueue(object: Callback<BookingMetaResponseList> {
            override fun onResponse(call: Call<BookingMetaResponseList>, response: Response<BookingMetaResponseList>) {

                when (response.code()){
                    200 -> responseBooking.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    404 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code", "code : ${response.code()}")

            }
            override fun onFailure(call: Call<BookingMetaResponseList>, t: Throwable) {
                errorLog.postValue(t.message)
            }

        })
    }

    fun getDetailBooking(token:String,bookingId: Int) {

        val response = repository.getDetailBooking(token,bookingId)

        response.enqueue(object: Callback<DetailBookingMetaList> {
            override fun onResponse(call: Call<DetailBookingMetaList>, response: Response<DetailBookingMetaList>) {

                when (response.code()){
                    200 -> responseData.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    404 -> errorLog.postValue("Server Error")
                }

                Log.d("Detail Status Code", "code : ${response.code()}")

            }
            override fun onFailure(call: Call<DetailBookingMetaList>, t: Throwable) {
                errorLog.postValue(t.message)
            }

        })
    }

}