package org.catrawi.atrawica.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.catrawi.atrawica.models.Meta
import org.catrawi.atrawica.models.MetaRegister
import org.catrawi.atrawica.models.User
import org.catrawi.atrawica.viewmodels.repository.RegisterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(
    private val  repository: RegisterRepository
) : ViewModel() {
    val errorLog = MutableLiveData<String>()

    fun register(data: User) {
        val response = repository.register(data)
        response.enqueue(object: Callback<MetaRegister> {
            override fun onResponse(call: Call<MetaRegister>, response: Response<MetaRegister>) {

            }

            override fun onFailure(call: Call<MetaRegister>, t: Throwable) {
                errorLog.postValue(t.message)
            }

        })

    }

}