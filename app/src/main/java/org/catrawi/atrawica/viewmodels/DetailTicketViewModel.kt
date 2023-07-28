package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.models.DetailBookingMeta
import org.catrawi.atrawica.models.DetailBookingTicket
import org.catrawi.atrawica.models.DetailBookingTicketMeta
import org.catrawi.atrawica.models.Ticket
import org.catrawi.atrawica.models.TicketMeta
import org.catrawi.atrawica.models.TicketRawResponse
import org.catrawi.atrawica.viewmodels.repository.DetailTicketRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTicketViewModel  (
    private val  repository: DetailTicketRepository
) : ViewModel() {

    val bookingTicketResponse = MutableLiveData<ArrayList<DetailBookingTicket>>()
    val ticketResponse = MutableLiveData<TicketRawResponse>()
    val detailTicketResponse = MutableLiveData<ArrayList<Ticket>>()
    val errorLog = MutableLiveData<String>()

    fun getDetailBooking(token: String, bookingId: Int) {
        val response = repository.getDetailTicket(token,bookingId)

        response.enqueue(object: Callback<DetailBookingTicketMeta> {
            override fun onResponse(call: Call<DetailBookingTicketMeta>, response: Response<DetailBookingTicketMeta>) {
                when (response.code()){
                    200 -> {
                        bookingTicketResponse.postValue(response.body()?.data)
                    }
                    401 -> errorLog.postValue("Not Found")
                    402 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code", "code: ${response.code()}")
            }

            override fun onFailure(call: Call<DetailBookingTicketMeta>, t: Throwable) {
                errorLog.postValue(t.message)
            }

        } )
    }

    fun getTicket(token: String,placeId : Int,departureId: Int) {
        val response = repository.getTicket(token,placeId,departureId)

        response.enqueue(object: Callback<TicketMeta> {
            override fun onResponse(call: Call<TicketMeta>, response: Response<TicketMeta>) {
                when (response.code()){
                    200 -> detailTicketResponse.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    402 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code Ticket", "code: ${response.code()}")
            }

            override fun onFailure(call: Call<TicketMeta>, t: Throwable) {
                Log.d("Error Found On Ticket",t.message.toString())
            }
        })
    }
}