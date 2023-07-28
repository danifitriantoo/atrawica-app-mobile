package org.catrawi.atrawica.views

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.catrawi.atrawica.MainActivity
import org.catrawi.atrawica.databinding.ActivityLoginBinding
import org.catrawi.atrawica.databinding.DialogLoadingBinding
import org.catrawi.atrawica.databinding.ModalSheetBinding
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.AuthViewModel
import org.catrawi.atrawica.viewmodels.factory.AuthViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.AuthRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    private lateinit var binding: ActivityLoginBinding
    private lateinit var modalSheetBinding: ModalSheetBinding
    private lateinit var dialogLoginBinding: DialogLoadingBinding

    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var progressDialog: Dialog
    private lateinit var data: Credential

    private val apiService = ApiService.getService()

    private var index: Int = 0

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Initialize binding **/
        binding = ActivityLoginBinding.inflate(layoutInflater)
        modalSheetBinding = ModalSheetBinding.inflate(layoutInflater)
        dialogLoginBinding = DialogLoadingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /** Initialize other late init **/
        viewModel = ViewModelProvider(this,
            AuthViewModelFactory(AuthRepository(apiService)))[AuthViewModel::class.java]

        progressDialog = Dialog(this)
        bottomSheetDialog = BottomSheetDialog(this)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            data = Credential(
                binding.etEmail.text.toString(),
                binding.keypassEditText.text.toString()
            )

            if (validation(data)) {
                progressDialog.setContentView(dialogLoginBinding.root)
                progressDialog.show()

                /** Asynchronously consume data from API  **/
                GlobalScope.launch(Dispatchers.IO) {

                    val result = async { viewModel.userAuth(data) }

                    result.await()

                    /** Dismissed progress dialog after data consumed  **/
                    progressDialog.dismiss()

                }

                viewModel.responseData.observe(this) {
                    when (it.code) {
                        200 -> {
                            SessionManager.saveAuthToken(this, it.data.authToken)
                            intentHome() }
                        else -> showModalSheet()
                    }
                }
            }

            if (!validation(data)) { showModalSheet() }

        }

    }

    override fun onResume() {
        super.onResume()

        if (!SessionManager.getToken(
                this).isNullOrBlank()) {
            intentHome()
        }
    }

    private fun intentHome() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun validation(data: Credential) : Boolean {

        var valid = true
        if (data.email == "" || data.keypass == "") { valid = false }
        return valid

    }

    private fun showModalSheet() {
        bottomSheetDialog.setContentView(modalSheetBinding.root)

        modalSheetBinding.btnConfirm.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

}