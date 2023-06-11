package com.exploreka.app

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.*
import com.exploreka.app.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), WisataAdapter.OnItemClickListener {
    private lateinit var bottomNavigationView: BottomNavigationView


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        val rv_wisata: RecyclerView = findViewById(R.id.rv_destinasi_wisata)
        val wisataList = listOf(
            Wisata(1, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(2, "Kepulauan Togian","Ambon, Maluku","4.9","321"),
            Wisata(3, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(4, "Kepulauan Togian","Ambon, Maluku","4.9","321")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )
        val wisataAdapter = WisataAdapter(wisataList)
        wisataAdapter.setOnItemClickListener(this)
        rv_wisata.adapter = wisataAdapter
        rv_wisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val WisataPopuler = findViewById<TextView>(R.id.tv_wisata_selengkapnya)
        WisataPopuler.setOnClickListener {
            // Intent ke aktivitas lain
            val intent = Intent(this, DestinasiWisataPopulerActivity::class.java)
            startActivity(intent)
        }


        val rv_paket_wisata: RecyclerView = findViewById(R.id.rv_paket_wisata)
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

        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NavigationActivity::class.java)
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
    override fun onItemClick(wisata: Wisata) {
        val intent = Intent(this, DetailWisataActivity::class.java)
        intent.putExtra("wisata", wisata)
        startActivity(intent)
    }
}
