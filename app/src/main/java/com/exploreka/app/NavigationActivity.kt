package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Navigation
import com.exploreka.app.data.Wisata
import com.exploreka.app.ui.NavigationAdapter
import com.exploreka.app.ui.WisataAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@NavigationActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val rv_navigation : RecyclerView = findViewById(R.id.rv_navigation)
        val navigationList = listOf(
            Navigation(1, "Karimun Jawa","4.5",),
            Navigation(2, "Kepulauan Togian","4.7"),
            Navigation(3, "Karimun Jawa","4.5"),
            Navigation(4, "Kepulauan Togian","4.5")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )
        val navigationAdapter = NavigationAdapter(navigationList)
        rv_navigation.adapter = navigationAdapter
        rv_navigation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_package -> {
                    val intent = Intent(this, PackageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_wishlist -> {
                    val intent = Intent(this, WishlistActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
            bottomNavigationView.selectedItemId = 0
            true
        }
    }
}