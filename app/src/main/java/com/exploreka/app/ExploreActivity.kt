package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Explore
import com.exploreka.app.ui.NavigationAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExploreActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@ExploreActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val rv_explore : RecyclerView = findViewById(R.id.rv_navigation)
        val exploreList = listOf(
            Explore(1, "Karimun Jawa","4.5",),
            Explore(2, "Kepulauan Togian","4.7"),
            Explore(3, "Karimun Jawa","4.5"),
            Explore(4, "Kepulauan Togian","4.5")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )
        val navigationAdapter = NavigationAdapter(exploreList)
        rv_explore.adapter = navigationAdapter
        rv_explore.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    menuItem.setIcon(R.drawable.bottom_nav_home_selector)
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.menu_package -> {
                    menuItem.setIcon(R.drawable.bottom_nav_package_selector)
                    startActivity(Intent(this, PackageActivity::class.java))
                    true
                }
                R.id.menu_wishlist -> {
                    menuItem.setIcon(R.drawable.bottom_nav_wishlist_selector)
                    startActivity(Intent(this, WishlistActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    menuItem.setIcon(R.drawable.bottom_nav_profile_selector)
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}