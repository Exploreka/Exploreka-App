package com.exploreka.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exploreka.app.R
import com.exploreka.app.retrofit.model.ModelArtikel

class ArtikelAdapter(var artikel: List<ModelArtikel>) :
    RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder>() {

    inner class ArtikelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artikelTitleTextView: TextView = itemView.findViewById(R.id.headline_news)
        private val artikelImageView: ImageView = itemView.findViewById(R.id.image_news)

        fun bind(artikel: ModelArtikel) {
            artikelTitleTextView.text = artikel.headlinePost

            // Load image using Glide if photoAttraction is not null
            artikel.imagePost?.let { photo ->
                Glide.with(itemView)
                    .load(photo.toString()) // Convert photo to String if necessary
                    .apply(RequestOptions().placeholder(R.drawable.karimunjawa))
                    .into(artikelImageView)
            } ?: run {
                // Set default image if photoAttraction is null
                artikelImageView.setImageResource(R.drawable.ic_baseline_image_24)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel_inspiratif_2, parent, false)
        return ArtikelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        val artikel = artikel[position]
        holder.bind(artikel)
    }

    override fun getItemCount(): Int {
        return artikel.size
    }
}
