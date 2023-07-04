package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.models.DetailBookingMeta
import org.catrawi.atrawica.models.Ticket
import org.catrawi.atrawica.models.TicketMeta
import org.catrawi.atrawica.viewmodels.repository.DetailBookingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailBookingViewModel(
    private val repository: DetailBookingRepository
): ViewModel() {

    val responseData = MutableLiveData<ArrayList<DetailBooking>>()
    val responseTicket = MutableLiveData<ArrayList<Ticket>>()
    val errorLog = MutableLiveData<String>()

    fun postBooking() {

        val response = repository.postDetailBooking(data = DetailBooking(
            0,0,0,"","", "","",0)
        )

        response.enqueue(object: Callback<DetailBookingMeta> {
            override fun onResponse(call: Call<DetailBookingMeta>, response: Response<DetailBookingMeta>) {
                when (response.code()) {
                    200 -> responseData.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    402 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code", "code: ${response.code()}")
            }

            override fun onFailure(call: Call<DetailBookingMeta>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    fun getDetailBooking() {
        val response = repository.getDetailBooking()

        response.enqueue(object: Callback<DetailBookingMeta>{
            override fun onResponse(call: Call<DetailBookingMeta>, response: Response<DetailBookingMeta>) {
                when (response.code()){
                    200 -> responseData.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    402 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code", "code: ${response.code()}")
            }

            override fun onFailure(call: Call<DetailBookingMeta>, t: Throwable) {
                errorLog.postValue(t.message)
                TODO("Not yet implemented")
            }

        } )
    }

    fun getTicket(placeId : Int,departureId: Int) {
        val response = repository.getTicket(placeId,departureId)

        response.enqueue(object: Callback<TicketMeta>{
            override fun onResponse(call: Call<TicketMeta>, response: Response<TicketMeta>) {
                when (response.code()){
                    200 -> responseTicket.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    402 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code", "code: ${response.code()}")
            }

            override fun onFailure(call: Call<TicketMeta>, t: Throwable) {
                Log.d("Error Found",t.message.toString())
            }
        })
    }
}