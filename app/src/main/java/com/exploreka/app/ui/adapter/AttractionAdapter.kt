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
import com.exploreka.app.retrofit.model.ModelAttraction

class AttractionAdapter(private val attractions: List<ModelAttraction>) :
    RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {

    // Definisikan interface listener
    interface OnItemClickListener {
        fun onItemClick(attraction: ModelAttraction)
    }

    // Deklarasikan variabel untuk menyimpan instance listener
    private var onItemClickListener: OnItemClickListener? = null

    // Metode setter untuk mengatur listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class AttractionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val attractionNameTextView: TextView = itemView.findViewById(R.id.tv_title_item)
        private val attractionCityTextView: TextView = itemView.findViewById(R.id.tv_location_item)
        private val attractionRatingTextView: TextView = itemView.findViewById(R.id.tv_reviewStar_item)
        private val attractionImageView: ImageView = itemView.findViewById(R.id.img_view_item)

        init {
            // Setel click listener pada item view
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Dapatkan objek attraction yang diklik
                    val attraction = attractions[position]
                    // Panggil metode onItemClick pada listener
                    onItemClickListener?.onItemClick(attraction)
                }
            }
        }

        fun bind(attraction: ModelAttraction) {
            attractionNameTextView.text = attraction.nameAttraction
            attractionCityTextView.text = attraction.city?.nameCity
            attractionRatingTextView.text = attraction.descAttraction

            // Setel teks rating jika ratingAvgAttraction tidak null
            attraction.ratingAvgAttraction?.let { rating ->
                attractionRatingTextView.text = rating.toString()
            } ?: run {
                attractionRatingTextView.text = ""
            }

            // Muat gambar menggunakan Glide jika photoAttraction tidak null
            attraction.photoAttraction?.let { photo ->
                Glide.with(itemView)
                    .load(photo.toString()) // Konversi photo menjadi String jika perlu
                    .apply(RequestOptions().placeholder(R.drawable.karimunjawa))
                    .into(attractionImageView)
            } ?: run {
                // Setel gambar default jika photoAttraction null
                attractionImageView.setImageResource(R.drawable.ic_baseline_image_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destinasi_populer, parent, false)
        return AttractionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction = attractions[position]
        holder.bind(attraction)
    }

    override fun getItemCount(): Int {
        return attractions.size
    }
}
