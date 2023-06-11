package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Wishlist
import com.exploreka.app.ui.WishlistAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WishlistActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)
        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)


        val recyclerView: RecyclerView = findViewById(R.id.rv_wishlist)
        val wishlistList = listOf(
            Wishlist(1, "Karimun Jawa","Rp 250.000"),
            Wishlist(2, "Kepulauan Togian","Rp 750.000"),
            Wishlist(3,"Karimun Jawa","Rp 250.0000"),
            Wishlist(4, "Karimun Jawa","Rp 250.000"),
            Wishlist(5, "Kepulauan Togian","Rp 750.000"),
            Wishlist(6,"Karimun Jawa","Rp 250.0000"),
            Wishlist(7, "Karimun Jawa","Rp 250.000"),
            Wishlist(8, "Kepulauan Togian","Rp 750.000"),
            Wishlist(9,"Karimun Jawa","Rp 250.0000"),
            Wishlist(10, "Karimun Jawa","Rp 250.000"),
            Wishlist(11, "Kepulauan Togian","Rp 750.000"),
            Wishlist(12,"Karimun Jawa","Rp 250.0000")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )
        val adapter = WishlistAdapter(wishlistList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@WishlistActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val help: ImageView = findViewById(R.id.help_button)
        help.setOnClickListener {
            val intent = Intent(this@WishlistActivity, HelpCenterActivity::class.java)
            startActivity(intent)
        }

        fab.setOnClickListener {
            val intent = Intent(this@WishlistActivity, NavigationActivity::class.java)
            startActivity(intent)
        }
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_wishlist
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
