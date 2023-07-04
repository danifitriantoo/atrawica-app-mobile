package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.ActivityDetailBinding
import org.catrawi.atrawica.databinding.ActivityLoginBinding
import org.catrawi.atrawica.databinding.FragmentEasyBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var id: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id",0);
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvDescription.text = intent.getStringExtra("description")

        Log.d("User Id",id.toString())

        binding.btnBooking.setOnClickListener {
            val intent = Intent(
                this,
                BookingActivity::class.java)

            intent.putExtra("id",id)
            startActivity(intent)
        }

    }
}