package org.catrawi.atrawica.views

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.catrawi.atrawica.MainActivity
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.ActivityLoginBinding
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.AuthViewModel
import org.catrawi.atrawica.viewmodels.factory.AuthViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.AuthRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private val apiService = ApiService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sessionManager = SessionManager(this)

        viewModel = ViewModelProvider(this, AuthViewModelFactory(AuthRepository(apiService)))[AuthViewModel::class.java]

        val progressDialog = Dialog(this)
        progressDialog.setContentView(R.layout.dialog_loading)
        progressDialog.setCancelable(false)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            intentHome()
        }


        binding.btnLogin.setOnClickListener {

            progressDialog.show()

            val data = Credential(
                binding.etEmail.text.toString(),
                binding.etKeypass.text.toString()
            )

            if(validation(data))
            {
                viewModel.userAuth(data)

                progressDialog.dismiss()

                viewModel.responseData.observe(this, Observer {

                SessionManager.saveAuthToken(this,it.data.authToken)

                    when(it.code) {
                        200 -> intentHome()
                        else -> showModalSheet()
                    }
                })

            }

            if(!validation(data)) { showModalSheet() }

        }

    }

    fun intentHome() {
        val intent = Intent(
            this,
            MainActivity::class.java)

        startActivity(intent)
    }

    fun validation(data: Credential) : Boolean {
        var valid = true

        if(data.email == "" || data.keypass == "") { valid = false }

        return valid
    }

    fun errorLog(code:Int) {
        Log.d("Error ", "code : $code")
    }

    fun showModalSheet() {

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.modal_sheet)

        dialog.findViewById<Button>(R.id.btn_confirm)?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}