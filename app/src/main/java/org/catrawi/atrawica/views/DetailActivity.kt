package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import org.catrawi.atrawica.databinding.ActivityDetailBinding
import org.catrawi.atrawica.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private var id: Int = 0
    private var price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id",0);
        price = intent.getIntExtra("price",0);

        binding.userNameTextView.text = intent.getStringExtra("name")
        binding.descriptionTextView.text = intent.getStringExtra("description")
        binding.placeTitleAppbar.title = intent.getStringExtra("name")

        Picasso.get().load(intent.getStringExtra("image")).into(binding.placeImageView)
        Picasso.get().load(intent.getStringExtra("image")).into(binding.detailPlaceImageView)

        binding.bookingButton.setOnClickListener {
            val intent = Intent(
                this,
                BookingActivity::class.java)

            intent.putExtra("id",id)
            intent.putExtra("price",price)
            startActivity(intent)
        }

    }
}