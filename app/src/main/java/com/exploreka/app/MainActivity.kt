package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NavigationActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_home
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
        }
    }
}
