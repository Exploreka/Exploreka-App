package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Artikel
import com.exploreka.app.ui.ArtikelAdapter

class ArtikelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artikel)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        val rv_10_artikel_inspiratif: RecyclerView = findViewById(R.id.rv_artikel)
        val artikelList = listOf(
            Artikel(1, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(2, "Kepulauan Togian, Pesona Indah di Tanah Maluku"),
            Artikel(3, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(4, "Kepulauan Togian,Pesona Indah di Tanah Maluku"),
            Artikel(5, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(6, "Kepulauan Togian, Pesona Indah di Tanah Maluku"),
            Artikel(7, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(8, "Kepulauan Togian,Pesona Indah di Tanah Maluku"),
            Artikel(9, "Karimun Jawa, Pesona Indah di Tanah Jawa"),
            Artikel(10, "Kepulauan Togian, Pesona Indah di Tanah Maluku")
            // Tambahkan paket wisata lainnya sesuai kebutuhan
        )
        val artikelAdapter = ArtikelAdapter(artikelList)
        rv_10_artikel_inspiratif.adapter = artikelAdapter

        val layoutManager = GridLayoutManager(this, 2)
        rv_10_artikel_inspiratif.layoutManager = layoutManager

    }
}