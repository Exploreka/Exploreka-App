package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

class SearchActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById(R.id.searchview)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Aksi yang dijalankan saat pengguna menekan tombol "Submit" pada keyboard
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Aksi yang dijalankan saat teks pencarian berubah
                // Misalnya, mengupdate daftar hasil pencarian secara real-time
                updateSearchResults(newText)
                return true
            }
        })

        // Di dalam SearchActivity
        val searchView: SearchView = findViewById(R.id.searchview)

        // Ambil data query dari Intent
        val query = intent.getStringExtra("query")

        // Set teks query ke SearchView
        searchView.setQuery(query, false)
    }

    private fun performSearch(query: String) {
        // Logika untuk melakukan pencarian berdasarkan query yang diberikan
        // Misalnya, melakukan filter pada data atau mengirim permintaan pencarian ke server
    }

    private fun updateSearchResults(newText: String) {
        // Logika untuk memperbarui daftar hasil pencarian berdasarkan teks baru yang dimasukkan
        // Misalnya, memfilter daftar data lokal secara real-time
    }
}