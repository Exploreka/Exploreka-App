package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.PaketWisata
import com.exploreka.app.data.Wisata
import com.exploreka.app.ui.PaketWisataAdapter
import com.exploreka.app.ui.WisataAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PackageActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package)
        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@PackageActivity, NavigationActivity::class.java)
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



        val rv_wisata_religi : RecyclerView = findViewById(R.id.rv_paket_wisata_religi)
        rv_wisata_religi.adapter = paketWisataAdapter
        rv_wisata_religi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)




        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_package
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