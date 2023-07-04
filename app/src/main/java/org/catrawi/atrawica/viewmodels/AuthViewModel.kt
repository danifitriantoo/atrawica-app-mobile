package org.catrawi.atrawica.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.AccessCredetial
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.viewmodels.repository.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(
    private val  repository: AuthRepository
) : ViewModel() {

    val responseData = MutableLiveData<AccessCredetial>()
    val errorLog = MutableLiveData<String>()

    fun userAuth(data: Credential) {

        val response = repository.userAuth(data)
        response.enqueue(object: Callback<Meta> {
            override fun onResponse(call: Call<Meta>, response: Response<Meta>) {
                responseData.postValue(response.body()?.meta)
            }

            override fun onFailure(call: Call<Meta>, t: Throwable) {
                errorLog.postValue(t.message)
            }

        })

    }

}