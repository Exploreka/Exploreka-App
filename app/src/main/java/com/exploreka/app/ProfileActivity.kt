package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_profile

        setupInState()
    }

    override fun onResume() {
        super.onResume()

        if (!checkUserLoginStatus()) {
            showLoginPopup()
        }
    }

    private fun checkUserLoginStatus(): Boolean {
        return false
    }

    private fun setupInState() {
        val fab: FloatingActionButton = findViewById(R.id.floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this@ProfileActivity, ExploreActivity::class.java)
            startActivity(intent)
        }

        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@ProfileActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val help: ImageView = findViewById(R.id.help_button)
        help.setOnClickListener {
            val intent = Intent(this@ProfileActivity, HelpCenterActivity::class.java)
            startActivity(intent)
        }

        val transaction: Button = findViewById(R.id.btn_transaction)
        transaction.setOnClickListener {
            val intent = Intent(this@ProfileActivity, ComingSoonActivity::class.java)
            startActivity(intent)
        }

        val helpdesk: Button = findViewById(R.id.btn_helpdesk)
        helpdesk.setOnClickListener {
            val intent = Intent(this@ProfileActivity, ComingSoonActivity::class.java)
            startActivity(intent)
        }

        val setting: Button = findViewById(R.id.btn_setting)
        setting.setOnClickListener {
            val intent = Intent(this@ProfileActivity, SettingActivity::class.java)
            startActivity(intent)
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    menuItem.setIcon(R.drawable.bottom_nav_home_selector)
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.menu_package -> {
                    menuItem.setIcon(R.drawable.bottom_nav_package_selector)
                    startActivity(Intent(this, PackageActivity::class.java))
                    true
                }
                R.id.menu_wishlist -> {
                    menuItem.setIcon(R.drawable.bottom_nav_wishlist_selector)
                    startActivity(Intent(this, WishlistActivity::class.java))
                    true
                }
                R.id.menu_profile -> {
                    menuItem.setIcon(R.drawable.bottom_nav_profile_selector)
                    true
                }
                else -> false
            }
        }
    }

    private fun showLoginPopup() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.popup_layout, null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        val btnPopupLogin = dialogView.findViewById<Button>(R.id.btn_popup_login)
        val btnPopupRegister = dialogView.findViewById<Button>(R.id.btn_popup_register)
        val btnPopupCancel = dialogView.findViewById<ImageButton>(R.id.btn_popup_cancel)

        btnPopupLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            alertDialog.dismiss()
        }

        btnPopupRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            alertDialog.dismiss()
        }
        btnPopupCancel.setOnClickListener {
            alertDialog.dismiss()
            setupInState()
        }
    }
}
