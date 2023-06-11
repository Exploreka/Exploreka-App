package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.PaketWisata
import com.exploreka.app.ui.PaketWisataAdapter

class PaketWisataMurahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_wisata_murah)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        val rv_10_paket_wisata_murah: RecyclerView = findViewById(R.id.rv_10_paket_wisata_murah)
        val paketWisataList = listOf(
            PaketWisata(1, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(2, "Kepulauan Togian", "Rp 750.0000", "4.9", "321"),
            PaketWisata(3, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(4, "Kepulauan Togian", "Rp 750.000", "4.9", "321"),
            PaketWisata(5, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(6, "Kepulauan Togian", "Rp 750.0000", "4.9", "321"),
            PaketWisata(7, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(8, "Kepulauan Togian", "Rp 750.000", "4.9", "321"),
            PaketWisata(9, "Karimun Jawa", "Rp 250.000", "4.6", "123"),
            PaketWisata(10, "Kepulauan Togian", "Rp 750.0000", "4.9", "321")
            // Tambahkan paket wisata lainnya sesuai kebutuhan
        )
        val paketWisataAdapter = PaketWisataAdapter(paketWisataList)
        rv_10_paket_wisata_murah.adapter = paketWisataAdapter

        val layoutManager = GridLayoutManager(this, 2)
        rv_10_paket_wisata_murah.layoutManager = layoutManager

    }
}