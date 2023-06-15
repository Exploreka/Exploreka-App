package com.exploreka.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.StreetViewPanoramaView
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import org.json.JSONException
import org.json.JSONObject

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
        val coordinateAttraction = intent.getStringExtra("coordinate_attraction")

        if (!coordinateAttraction.isNullOrEmpty()) {
            try {
                val coordinateObject = JSONObject(coordinateAttraction)
                val lat = coordinateObject.optDouble("lat", 0.0)
                val lng = coordinateObject.optDouble("lng", 0.0)

                panorama.isStreetNamesEnabled = true
                panorama.isUserNavigationEnabled = true
                panorama.setPosition(LatLng(lat, lng))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
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
