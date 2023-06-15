package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.PaketWisata
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelTourPackage
import com.exploreka.app.ui.PaketWisataAdapter
import com.exploreka.app.ui.adapter.TourPackageAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaketWisataGunungActivity : AppCompatActivity() {

    private lateinit var tourpackageAdapter: TourPackageAdapter
    private lateinit var apiService: ApiService
    private lateinit var rv_10_paket_wisata_gunung : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_wisata_gunung)

        apiService = ApiClient.getInstance()

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        rv_10_paket_wisata_gunung = findViewById(R.id.rv_10_paket_wisata_gunung)

        val layoutManager = GridLayoutManager(this, 2)
        rv_10_paket_wisata_gunung.layoutManager = layoutManager

        fetchDataPaketGunung()
    }
    private fun fetchDataPaketGunung() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getTourPackage()
                if (response.status == "Success") {
                    val tourpackage = response.data
                    tourpackageAdapter = tourpackage?.let { TourPackageAdapter(it) }!!
                    rv_10_paket_wisata_gunung.adapter = tourpackageAdapter
                    tourpackageAdapter.setOnItemClickListener(object : TourPackageAdapter.OnItemClickListener {
                        override fun onItemClick(tourpackage: ModelTourPackage) {
                            // Tangani acara klik item
                            val intent = Intent(this@PaketWisataGunungActivity, DetailPackageActivity::class.java)
                            intent.putExtra("tourpackageId", tourpackage.idTourPackage.toString() )
                            startActivity(intent)
                        }
                    })

                    rv_10_paket_wisata_gunung.adapter
                } else {
                    Toast.makeText(this@PaketWisataGunungActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PaketWisataGunungActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
}