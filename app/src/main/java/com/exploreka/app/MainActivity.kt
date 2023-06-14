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
import com.exploreka.app.ui.*
import com.exploreka.app.ui.adapter.AttractionAdapter
import com.exploreka.app.ui.adapter.TourPackageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.search.SearchBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), WisataAdapter.OnItemClickListener {
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var tourpackageAdapter: TourPackageAdapter
    private lateinit var apiService: ApiService
    private lateinit var rv_destinasi_wisata: RecyclerView


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


        val rv_paket_wisata: RecyclerView = findViewById(R.id.rv_paket_wisata)
        rv_paket_wisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val PaketPopuler = findViewById<TextView>(R.id.tv_paket_selengkapnya)
        PaketPopuler.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, PaketWisataPopulerActivity::class.java)
            startActivity(intent)
        }


        val rv_artikel_inspiratif: RecyclerView = findViewById(R.id.rv_artikel)
        val artikelList = listOf(
            Artikel(1, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(2, "Kepulauan Togian, Pesona Indah di Tanah Maluku"),
            Artikel(3, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(4, "Kepulauan Togian,Pesona Indah di Tanah Maluku")
            // Tambahkan paket wisata lainnya sesuai kebutuhan
        )
        val artikelAdapter = ArtikelAdapter(artikelList)
        rv_artikel_inspiratif.adapter = artikelAdapter
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

//        val searchBar = findViewById<SearchBar>(R.id.search_bar)
//
//        searchBar.setOnSearchActionListener(object : SearchBar.OnSearchActionListener {
//            override fun onSearchStateChanged(enabled: Boolean) {
//                // Tindakan yang diambil saat status pencarian berubah
//            }
//
//            override fun onSearchConfirmed(text: CharSequence) {
//                val attractionName = text.toString()
//                searchAttractionByName(attractionName)
//            }
//
//            override fun onButtonClicked(buttonCode: Int) {
//                if (buttonCode == SearchBar.BUTTON_NAVIGATION) {
//                    // Tindakan saat tombol navigasi di search bar ditekan
//                }
//            }
//        })
//
//        private fun searchAttractionByName(attractionName: String) {
//            // Panggil fungsi pencarian API di sini dan lakukan penanganan hasil pencarian
//            // Misalnya, Anda dapat menggunakan Retrofit untuk melakukan pemanggilan API
//
//            // Buat instance dari Retrofit
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            // Buat instance ApiService menggunakan Retrofit
//            val apiService = retrofit.create(ApiService::class.java)
//
//            // Panggil endpoint pencarian di ApiService
//            val call = apiService.searchAttractionsByName(attractionName)
//
//            // Lakukan pemanggilan API secara asynchronous menggunakan enqueue
//            call.enqueue(object : Callback<AttractionsResponse> {
//                override fun onResponse(
//                    call: Call<AttractionsResponse>,
//                    response: Response<AttractionsResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val attractionsResponse = response.body()
//                        if (attractionsResponse?.status == "Success") {
//                            val attractions = attractionsResponse.data
//                            // Lakukan sesuatu dengan daftar tempat wisata yang ditemukan
//                            for (attraction in attractions) {
//                                val attractionName = attraction.nameAttraction
//                                val cityName = attraction.city?.nameCity
//                                val photoAttraction = attraction.photoAttraction
//
//                                // Tampilkan informasi tempat wisata kepada pengguna
//                                println("Nama Tempat Wisata: $attractionName")
//                                println("Kota: $cityName")
//                                println("Foto: $photoAttraction")
//                                println()
//                            }
//                        } else {
//                            val errorMessage = attractionsResponse?.message
//                            println("Error: $errorMessage")
//                        }
//                    } else {
//                        println("Error: ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<AttractionsResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            })
//        }


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


    override fun onItemClick(wisata: Wisata) {
        val intent = Intent(this, DetailWisataActivity::class.java)
        intent.putExtra("wisata", wisata)
        startActivity(intent)
    }
}
