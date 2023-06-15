package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById(R.id.searchview_layout)
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
        val searchView: SearchView = findViewById(R.id.searchview_layout)

        // Ambil data query dari Intent
        val query = intent.getStringExtra("query")

        // Set teks query ke SearchView
        searchView.setQuery(query, false)

        // Set focus dan buka keyboard secara otomatis pada SearchView
        searchView.isIconified = false
        searchView.requestFocus()

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