package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.PaketWisata
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.ui.PaketWisataAdapter
import com.exploreka.app.ui.adapter.AttractionAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaketWisataPopulerActivity : AppCompatActivity() {

    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var rv_10_paket_wisata: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_wisata_populer)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        rv_10_paket_wisata = findViewById(R.id.rv_10_paket_wisata) // Hapus deklarasi ulang tipe data RecyclerView di sini

        val layoutManager = GridLayoutManager(this, 2)
        rv_10_paket_wisata.layoutManager = layoutManager

    }

}