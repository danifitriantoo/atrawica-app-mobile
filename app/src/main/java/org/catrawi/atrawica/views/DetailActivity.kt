package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.catrawi.atrawica.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var id: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id",0);

        binding.placeNameTextView.text = intent.getStringExtra("name")
        binding.descriptionTextView.text = intent.getStringExtra("description")
        binding.placeTitleAppbar.title = intent.getStringExtra("name")

        Log.d("User Id",id.toString())

        binding.bookingButton.setOnClickListener {
            val intent = Intent(
                this,
                BookingActivity::class.java)

            intent.putExtra("id",id)
            startActivity(intent)
        }

    }
}