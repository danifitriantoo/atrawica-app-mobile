package org.catrawi.atrawica.views

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.ActivityLoginBinding
import org.catrawi.atrawica.databinding.ActivityTicketBinding
import org.catrawi.atrawica.databinding.FragmentMyTicketBinding
import org.catrawi.atrawica.views.fragments.HomeFragment
import org.catrawi.atrawica.views.fragments.Tickets.BackFragment
import org.catrawi.atrawica.views.fragments.Tickets.DepartFragment

class TicketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTicketBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadFragment(DepartFragment())

        val tabLayout = binding.tlActionCheckout

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.text == "Berangkat") {
                    loadFragment(DepartFragment())
                } else {
                    loadFragment(BackFragment())
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if(tab?.text == "Pulang") {
                    destroyFragment(DepartFragment())
                } else {
                    destroyFragment(BackFragment())
                }
            }
        })

    }

//    fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fl_route,fragment)
//        transaction.commit()
//    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_route,fragment)
        transaction.commit()
    }

    private fun destroyFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().detach(fragment).commit()
    }

}