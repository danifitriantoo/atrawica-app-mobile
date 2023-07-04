package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.viewmodels.repository.TestRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestViewModel(
    private val  repository: TestRepository
) : ViewModel() {

    val responseData = MutableLiveData<ArrayList<Place>>()
    val errorLog = MutableLiveData<String>()

    fun getAllPlace() : Int {

        var resposeCode : Int = 404
        val response = repository.getAllPlace()

        response.enqueue(object: Callback<PlaceMeta> {
            override fun onResponse(call: Call<PlaceMeta>, response: Response<PlaceMeta>) {
                resposeCode =  response.code()
            }
            override fun onFailure(call: Call<PlaceMeta>, t: Throwable) {
                errorLog.postValue(t.message)
                TODO("Not yet implemented")
            }

        })
        return resposeCode
    }

    fun userAuth(data: Credential): Int {

        var resposeCode : Int = 404

        val response = repository.userAuth(data)
        response.enqueue(object: Callback<Meta> {
            override fun onResponse(call: Call<Meta>, response: Response<Meta>) {
                resposeCode = response.code()
            }

            override fun onFailure(call: Call<Meta>, t: Throwable) {
                errorLog.postValue(t.message)
            }

        })
        return resposeCode
    }

}