package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val  repository: HomeRepository
) : ViewModel() {

    val responseData = MutableLiveData<ArrayList<Place>>()
    val errorLog = MutableLiveData<String>()

    fun getAllPlace() {

        val response = repository.getAllPlace()

        response.enqueue(object: Callback<PlaceMeta> {
            override fun onResponse(call: Call<PlaceMeta>, response: Response<PlaceMeta>) {

                when (response.code()){
                    200 -> responseData.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Not Found")
                    404 -> errorLog.postValue("Server Error")
                }

                Log.d("Status Code", "code : ${response.code()}")

            }
            override fun onFailure(call: Call<PlaceMeta>, t: Throwable) {
                errorLog.postValue(t.message)
                TODO("Not yet implemented")
            }

        })
    }

}