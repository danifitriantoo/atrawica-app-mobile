package org.catrawi.atrawica.views

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import org.catrawi.atrawica.R
import org.catrawi.atrawica.views.fragments.HomeFragment
import org.catrawi.atrawica.views.fragments.Tickets.DepartFragment

class TicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        loadFragment(DepartFragment())
    }

    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_route,fragment)
        transaction.commit()
    }
}