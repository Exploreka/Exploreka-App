package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng

class ViewWisataActivity : AppCompatActivity(), OnStreetViewPanoramaReadyCallback {
    private lateinit var streetView: StreetViewPanoramaView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_wisata)

        streetView = findViewById(R.id.street_view)
        streetView.onCreate(savedInstanceState)
        streetView.getStreetViewPanoramaAsync(this)
    }

    override fun onStreetViewPanoramaReady(panorama: StreetViewPanorama) {
        val latitude = -6.9585233 // Latitude yang diinginkan
        val longitude = 110.4700825 // Longitude yang diinginkan

        panorama.isStreetNamesEnabled = true
        panorama.isUserNavigationEnabled = true
        panorama.setPosition(LatLng(latitude, longitude))
    }

    override fun onResume() {
        super.onResume()
        streetView.onResume()
    }

    override fun onPause() {
        super.onPause()
        streetView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        streetView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        streetView.onLowMemory()
    }
}
