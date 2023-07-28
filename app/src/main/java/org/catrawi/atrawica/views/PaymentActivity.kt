package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGopay.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("PayId",1)
            startActivity(intent)
        }

        binding.btnOvo.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("PayId",2)
            startActivity(intent)
        }

        binding.btnAtm.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("PayId",3)
            startActivity(intent)
        }
    }
}