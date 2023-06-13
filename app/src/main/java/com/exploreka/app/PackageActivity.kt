package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.PaketWisata
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.ui.PaketWisataAdapter
import com.exploreka.app.ui.adapter.ArtikelAdapter
import com.exploreka.app.ui.adapter.AttractionAdapter
import com.exploreka.app.ui.adapter.TourPackageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PackageActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var tourpackageAdapter: TourPackageAdapter
    private lateinit var artikelAdapter: ArtikelAdapter
    private lateinit var apiService: ApiService
    private lateinit var rv_wisata_murah : RecyclerView
    private lateinit var rv_wisata_religi : RecyclerView
    private lateinit var rv_paket_gunung : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package)

        apiService = ApiClient.getInstance()

        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@PackageActivity, ExploreActivity::class.java)
            startActivity(intent)
        }



        rv_wisata_murah  = findViewById(R.id.rv_paket_wisata_murah)
        rv_wisata_murah.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val PaketMurah = findViewById<TextView>(R.id.tv_selengkapnya_murah)
        PaketMurah.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataMurahActivity::class.java)
            startActivity(intent)
        }


        rv_wisata_religi  = findViewById(R.id.rv_paket_wisata_religi)
        rv_wisata_religi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val PaketReligi = findViewById<TextView>(R.id.tv_selengkapnya_religi)
        PaketReligi.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataReligiActivity::class.java)
            startActivity(intent)
        }

        rv_paket_gunung = findViewById(R.id.rv_paket_wisata_gunung)
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
        fetchDataPaketMurah()
        fetchDataPaketReligi()
        fetchDataPaketGunung()

    }
    private fun fetchDataPaketMurah() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getTourPackage()
                if (response.status == "Success") {
                    val tourpackage = response.data
                    tourpackageAdapter = tourpackage?.let { TourPackageAdapter(it) }!!
                    rv_wisata_murah.adapter = tourpackageAdapter
                } else {
                    Toast.makeText(this@PackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                    .show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
    private fun fetchDataPaketReligi() {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getTourPackage()
                if (response.status == "Success") {
                    val tourpackage = response.data
                    tourpackageAdapter = tourpackage?.let { TourPackageAdapter(it) }!!
                    rv_wisata_religi.adapter = tourpackageAdapter
                } else {
                    Toast.makeText(this@PackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                    .show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
    private fun fetchDataPaketGunung() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getTourPackage()
                if (response.status == "Success") {
                    val tourpackage = response.data
                    tourpackageAdapter = tourpackage?.let { TourPackageAdapter(it) }!!
                    rv_paket_gunung.adapter = tourpackageAdapter
                } else {
                    Toast.makeText(this@PackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                    .show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
}