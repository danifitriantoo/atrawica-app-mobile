package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.PendingIntentCompat
import androidx.core.view.ContentInfoCompat.Flags
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.ActivityRegisterBinding
import org.catrawi.atrawica.models.User
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.RegisterViewModel
import org.catrawi.atrawica.viewmodels.factory.RegisterViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.RegisterRepository

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel
    private val apiService = ApiService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sessionManager = SessionManager(this)

        viewModel = ViewModelProvider(
            this, RegisterViewModelFactory(
                RegisterRepository(apiService)))[RegisterViewModel::class.java]

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {

            val user = User(
                0,
                binding.etNama.text.toString(),
                binding.etEmail.text.toString(),
                binding.etNoHp.text.toString(),
                binding.etConfirmKeypass.text.toString(),
                0)

            if(validation(user)) {
                viewModel.register(user)
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            if(!validation(user)) { showModalSheet() }

        }
    }

    fun validation(data: User) : Boolean {
        var valid = true

        if(data.name == "" || data.email == ""
            || data.noHp == "" || data.keypass == ""
        ) { valid = false }

        return valid
    }

    fun showModalSheet() {

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.modal_sheet)

        dialog.findViewById<Button>(R.id.btn_confirm)?.setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.tv_title)?.text =
            "Registrasi Gagal"
        dialog.findViewById<TextView>(R.id.tv_content)?.text =
            "Periksa lagi yuk, pastikan semua field\n terisi biar akunmu bisa kami proses."

        dialog.show()
    }
}