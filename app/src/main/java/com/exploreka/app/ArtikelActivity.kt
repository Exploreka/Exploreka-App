package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.ui.adapter.ArtikelAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtikelActivity : AppCompatActivity() {

    private lateinit var rv_artikel: RecyclerView
    private lateinit var artikelAdapter: ArtikelAdapter
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artikel)

        apiService = ApiClient.getInstance()

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        rv_artikel = findViewById(R.id.rv_artikel)
        val layoutManager = GridLayoutManager(this, 2)
        rv_artikel.layoutManager = layoutManager

        fetchDataArtikel()
    }

    private fun fetchDataArtikel() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getArtikel()
                if (response.status == "Success") {
                    val artikel = response.data
                    artikelAdapter = artikel?.let { ArtikelAdapter(it) }!!
                    rv_artikel.adapter = artikelAdapter
                } else {
                    Toast.makeText(this@ArtikelActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ArtikelActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
}