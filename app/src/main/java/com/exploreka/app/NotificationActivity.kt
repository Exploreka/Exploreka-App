package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

    }
}