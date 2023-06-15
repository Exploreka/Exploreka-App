package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val editText: Button = findViewById(R.id.btn_edit_profile)
        editText.setOnClickListener {
            val intent = Intent(this@SettingActivity, ComingSoonActivity::class.java)
            startActivity(intent)
        }

        val changePassword: Button = findViewById(R.id.btn_ubah_password)
        changePassword.setOnClickListener {
            val intent = Intent(this@SettingActivity, ComingSoonActivity::class.java)
            startActivity(intent)
        }

        val mediaSocial: Button = findViewById(R.id.btn_media_social)
        mediaSocial.setOnClickListener {
            val intent = Intent(this@SettingActivity, ComingSoonActivity::class.java)
            startActivity(intent)
        }

        val changeLanguage: Button = findViewById(R.id.btn_change_language)
        changeLanguage.setOnClickListener {
            val intent = Intent(this@SettingActivity, ComingSoonActivity::class.java)
            startActivity(intent)
        }
    }
}