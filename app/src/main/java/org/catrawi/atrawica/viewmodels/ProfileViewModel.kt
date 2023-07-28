package org.catrawi.atrawica.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.models.MetaRegister
import org.catrawi.atrawica.models.Ticket
import org.catrawi.atrawica.models.User
import org.catrawi.atrawica.viewmodels.repository.ProfileRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel (
    private val repository: ProfileRespository
): ViewModel() {

    val responseData = MutableLiveData<User>()
    val errorLog = MutableLiveData<String>()

    fun updateBudget(token:String,user: User) {
        val response = repository.updateBudget(token,user)
        response.enqueue(object : Callback<MetaRegister> {
            override fun onResponse(call: Call<MetaRegister>, response: Response<MetaRegister>) {
                responseData.postValue(response.body()?.data)
                Log.d("Update Budget Status",response.code().toString())
            }

            override fun onFailure(call: Call<MetaRegister>, t: Throwable) {

            }

        })
    }
}