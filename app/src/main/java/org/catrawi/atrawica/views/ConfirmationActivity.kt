package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.catrawi.atrawica.MainActivity
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.ActivityBookingBinding
import org.catrawi.atrawica.databinding.ActivityConfirmationBinding
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.AuthViewModel

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSucessLogin.setOnClickListener {
            val intent = Intent(
                this,
                MainActivity::class.java)

            startActivity(intent)
        }
    }
}