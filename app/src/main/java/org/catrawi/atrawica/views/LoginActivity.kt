package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.MainActivity
import org.catrawi.atrawica.databinding.ActivityLoginBinding
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.AuthViewModel
import org.catrawi.atrawica.viewmodels.factory.AuthViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.AuthRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private val apiService = ApiService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sessionManager = SessionManager(this)

        authViewModel = ViewModelProvider(this, AuthViewModelFactory(AuthRepository(apiService)))
            .get(AuthViewModel::class.java)


        binding.btnLogin.setOnClickListener {
            val data = Credential(
                binding.etEmail.text.toString(),
                binding.etKeypass.text.toString()
            )

            authViewModel.responseData.observe(this, Observer {

                when(it.code) {
                    200 -> intentHome()
                    204 -> errorLog(it.code)
                    205 -> errorLog(it.code)
                    else -> errorLog(it.code)
                }
            })

            authViewModel.errorLog.observe(this, Observer {
                Log.d("Error ", "errorMessage: $it")
            })


            authViewModel.userAuth(data)
        }

    }

    fun intentHome() {
        val intent = Intent(
            this,
            MainActivity::class.java)

        startActivity(intent)
    }

    fun errorLog(code:Int) {
        Log.d("Error ", "code : $code")
    }


}