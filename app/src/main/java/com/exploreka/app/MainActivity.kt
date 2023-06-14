package com.exploreka.app

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.*
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.ui.*
import com.exploreka.app.ui.adapter.ArtikelAdapter
import com.exploreka.app.ui.adapter.AttractionAdapter
import com.exploreka.app.ui.adapter.TourPackageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var tourpackageAdapter: TourPackageAdapter
    private lateinit var artikelAdapter: ArtikelAdapter
    private lateinit var apiService: ApiService
    private lateinit var rv_destinasi_wisata: RecyclerView
    private lateinit var rv_paket_wisata: RecyclerView
    private lateinit var rv_artikel_inspiratif: RecyclerView


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiClient.getInstance()

        val rvKategori: RecyclerView = findViewById(R.id.rv_kategori)
        val categoryList = listOf(
            Category(1, "Budaya"),
            Category(2, "Taman Hiburan"),
            Category(3, "CagarAlam"),
            Category(4, "Bahari"),
            Category(5, "Tempat Ibadah"),
            Category(6, "Pusat Belanja"),
            Category(7, "Kategori Lain")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )
        val kategoriAdapter = CategoryAdapter(categoryList)
        rvKategori.adapter = kategoriAdapter

        val layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1 // Lebar setiap item adalah 1 kolom
            }
        }
        rvKategori.layoutManager = layoutManager


        rv_destinasi_wisata = findViewById(R.id.rv_destinasi_wisata)
        rv_destinasi_wisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        val WisataPopuler = findViewById<TextView>(R.id.tv_wisata_selengkapnya)
        WisataPopuler.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, DestinasiWisataPopulerActivity::class.java)
            startActivity(intent)
        }


        rv_paket_wisata = findViewById(R.id.rv_paket_wisata)
        rv_paket_wisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val PaketPopuler = findViewById<TextView>(R.id.tv_paket_selengkapnya)
        PaketPopuler.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataPopulerActivity::class.java)
            startActivity(intent)
        }


        rv_artikel_inspiratif = findViewById(R.id.rv_artikel)
        rv_artikel_inspiratif.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val ArtikelPopuler = findViewById<TextView>(R.id.tv_artikel_selengkapnya)
        ArtikelPopuler.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, ArtikelActivity::class.java)
            startActivity(intent)
        }


        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@MainActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val help: ImageView = findViewById(R.id.help_button)
        help.setOnClickListener {
            val intent = Intent(this@MainActivity, HelpCenterActivity::class.java)
            startActivity(intent)
        }

        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, ExploreActivity::class.java)
            startActivity(intent)
        }

        val dss: Button = findViewById(R.id.btn_dss)
        dss.setOnClickListener {
            val intent = Intent(this@MainActivity, DssActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_home
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    menuItem.setIcon(R.drawable.bottom_nav_home_selector)
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

        fetchDataWisata()
        fetchDataPaket()
        fetchDataArtikel()
    }


    private fun fetchDataWisata() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractions()
                if (response.status == "Success") {
                    val attractions = response.data
                    attractionAdapter = attractions?.let { AttractionAdapter(it) }!!
                    rv_destinasi_wisata.adapter = attractionAdapter

                    // Atur click listener item pada adapter
                    attractionAdapter.setOnItemClickListener(object : AttractionAdapter.OnItemClickListener {
                        override fun onItemClick(attraction: ModelAttraction) {
                            // Tangani acara klik item
                            val intent = Intent(this@MainActivity, DetailWisataActivity::class.java)
                            // Kirim data yang diperlukan ke DetailWisataActivity menggunakan intent
                            intent.putExtra("attractionId", attraction.idAttraction)
                            startActivity(intent)
                        }
                    })

                    rv_destinasi_wisata.adapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }

    private fun fetchDataPaket() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getTourPackage()
                if (response.status == "Success") {
                    val tourpackage = response.data
                    tourpackageAdapter = tourpackage?.let { TourPackageAdapter(it) }!!
                    rv_paket_wisata.adapter = tourpackageAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }

    private fun fetchDataArtikel() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getArtikel()
                if (response.status == "Success") {
                    val artikel = response.data
                    artikelAdapter = artikel?.let { ArtikelAdapter(it) }!!
                    rv_artikel_inspiratif.adapter = artikelAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }


}
