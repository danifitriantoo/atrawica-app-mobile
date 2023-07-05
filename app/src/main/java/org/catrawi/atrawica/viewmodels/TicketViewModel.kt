package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.Ticket
import org.catrawi.atrawica.models.TicketMeta
import org.catrawi.atrawica.viewmodels.repository.TicketRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TicketViewModel(
    private val repository: TicketRepository
): ViewModel() {

    val responseData = MutableLiveData<ArrayList<Ticket>>()
    val errorLog = MutableLiveData<String>()

    fun getTicket(placeId : Int,departureId: Int) {
        val response = repository.getTicket(placeId,departureId)

        response.enqueue(object: Callback<TicketMeta> {
            override fun onResponse(call: Call<TicketMeta>, response: Response<TicketMeta>) {
                when (response.code()){
                    200 -> responseData.postValue(response.body()?.data)
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
