package org.catrawi.atrawica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.views.fragments.FaqFragment
import org.catrawi.atrawica.views.fragments.FavoriteFragment
import org.catrawi.atrawica.views.fragments.HomeFragment
import org.catrawi.atrawica.views.fragments.MyTicketFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())

        val token = SessionManager.getToken(this)

        bottomNav = findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_fragment -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.home_ticket -> {
                    loadFragment(MyTicketFragment())
                    true
                }
                R.id.home_fav -> {
                    loadFragment(FavoriteFragment())
                    true
                }
                R.id.home_faq -> {
                    loadFragment(FaqFragment())
                    true
                }
                else -> {
                    loadFragment(FavoriteFragment())
                    true
                }
            }

        }
    }
    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment,fragment)
        transaction.commit()
    }
}
