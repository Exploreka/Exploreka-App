package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Wisata
import com.exploreka.app.ui.WisataAdapter

class DestinasiWisataPopulerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinasi_wisata_populer)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        val rv_10_wisata: RecyclerView = findViewById(R.id.rv_10_destinasi_wisata)
        val wisataList = listOf(
            Wisata(1, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(2, "Kepulauan Togian","Ambon, Maluku","4.9","321"),
            Wisata(3, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(4, "Kepulauan Togian","Ambon, Maluku","4.9","321"),
            Wisata(5, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(6, "Kepulauan Togian","Ambon, Maluku","4.9","321"),
            Wisata(7, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(8, "Kepulauan Togian","Ambon, Maluku","4.9","321"),
            Wisata(9, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(10, "Kepulauan Togian","Ambon, Maluku","4.9","321")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )
        val wisataAdapter = WisataAdapter(wisataList)
        rv_10_wisata.adapter = wisataAdapter

        val layoutManager = GridLayoutManager(this, 2)
        rv_10_wisata.layoutManager = layoutManager

    }
}