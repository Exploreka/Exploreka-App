package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiClientMachineLeaning
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.retrofit.model.ModelInputContentBaseRecommend
import com.exploreka.app.retrofit.model.ModelResponseContentBaseRecommend
import com.exploreka.app.ui.adapter.AttractionAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var rv_search: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val notif: ImageView = findViewById(R.id.notification_button)
        notif.setOnClickListener {
            val intent = Intent(this@SearchActivity, NotificationActivity::class.java)
            startActivity(intent)
        }

        val help: ImageView = findViewById(R.id.help_button)
        help.setOnClickListener {
            val intent = Intent(this@SearchActivity, HelpCenterActivity::class.java)
            startActivity(intent)
        }

        searchView = findViewById(R.id.searchview)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchAttractions(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchAttractions(it) }
                return true
            }
        })

        rv_search = findViewById(R.id.rv_search)
        val layoutManager = GridLayoutManager(this, 2)
        rv_search.layoutManager = layoutManager

        apiService = ApiClientMachineLeaning.getInstance()

        val query = intent.getStringExtra("query")
        searchView.setQuery(query, false)
    }

    private fun searchAttractions(query: String) {
        val request = ModelInputContentBaseRecommend(query)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = apiService.searchAttraction(request)
                if (response.isSuccessful) {
                    val recommendedPlaces = response.body()?.recommendedPlaces
                    recommendedPlaces?.let {
                        val attractions = it.map { attractionData ->
                            ModelAttraction(
                                descAttraction = null, // Ubah sesuai kebutuhan Anda
                                photoAttraction = null, // Ubah sesuai kebutuhan Anda
                                city = null, // Ubah sesuai kebutuhan Anda
                                attractionCategory = null, // Ubah sesuai kebutuhan Anda
                                priceAttraction = null, // Ubah sesuai kebutuhan Anda
                                closeHour = null, // Ubah sesuai kebutuhan Anda
                                idCity = null, // Ubah sesuai kebutuhan Anda
                                nameAttraction = attractionData?.get(0) ?: "",
                                ratingAvgAttraction = null, // Ubah sesuai kebutuhan Anda
                                idAttractionCat = null, // Ubah sesuai kebutuhan Anda
                                openHour = null, // Ubah sesuai kebutuhan Anda
                                idAttraction = null, // Ubah sesuai kebutuhan Anda
                                coordinateAttraction = null // Ubah sesuai kebutuhan Anda
                            )
                        }
                        attractionAdapter = AttractionAdapter(attractions)
                        rv_search.adapter = attractionAdapter
                    }

                } else {
                    // Tangani error jika perlu
                }
            } catch (e: Exception) {
                // Tangani error jika perlu
            }
        }
    }
}
