package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.PaketWisata
import com.exploreka.app.ui.PaketWisataAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PackageActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package)
        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@PackageActivity, ExploreActivity::class.java)
            startActivity(intent)
        }



        val rv_paket_wisata: RecyclerView = findViewById(R.id.rv_paket_wisata_murah)
        val paketWisataList = listOf(
            PaketWisata(1, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(2, "Kepulauan Togian", "Rp 750.0000", "4.9", "321"),
            PaketWisata(3, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(4, "Kepulauan Togian", "Rp 750.000", "4.9", "321")
            // Tambahkan paket wisata lainnya sesuai kebutuhan
        )
        val paketWisataAdapter = PaketWisataAdapter(paketWisataList)
        rv_paket_wisata.adapter = paketWisataAdapter
        rv_paket_wisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val PaketMurah = findViewById<TextView>(R.id.tv_selengkapnya_murah)
        PaketMurah.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataMurahActivity::class.java)
            startActivity(intent)
        }


        val rv_wisata_religi : RecyclerView = findViewById(R.id.rv_paket_wisata_religi)
        rv_wisata_religi.adapter = paketWisataAdapter
        rv_wisata_religi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val PaketReligi = findViewById<TextView>(R.id.tv_selengkapnya_religi)
        PaketReligi.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataReligiActivity::class.java)
            startActivity(intent)
        }

        val rv_paket_gunung : RecyclerView = findViewById(R.id.rv_paket_wisata_gunung)
        rv_paket_gunung.adapter = paketWisataAdapter
        rv_paket_gunung.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val PaketGunung = findViewById<TextView>(R.id.tv_selengkapnya_gunung)
        PaketGunung.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataGunungActivity::class.java)
            startActivity(intent)
        }

        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@PackageActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val help: ImageView = findViewById(R.id.help_button)
        help.setOnClickListener {
            val intent = Intent(this@PackageActivity, HelpCenterActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_package
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    menuItem.setIcon(R.drawable.bottom_nav_home_selector)
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.menu_package -> {
                    menuItem.setIcon(R.drawable.bottom_nav_package_selector)
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