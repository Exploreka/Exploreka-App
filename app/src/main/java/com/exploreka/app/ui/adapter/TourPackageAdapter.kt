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
import com.exploreka.app.retrofit.model.ModelTourPackage

class TourPackageAdapter(var packagetour: List<ModelTourPackage>) :
    RecyclerView.Adapter<TourPackageAdapter.TourPackageViewHolder>() {

    inner class TourPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tourpackageNameTextView: TextView = itemView.findViewById(R.id.tv_title_paket)
        private val tourpackagePriceTextView: TextView = itemView.findViewById(R.id.tv_price_paket)
        private val tourpackageRatingTextView: TextView = itemView.findViewById(R.id.tv_reviewStar_paket)
        private val tourpackageImageView: ImageView = itemView.findViewById(R.id.img_view_paket)

        fun bind(tourpackage: ModelTourPackage) {
            tourpackageNameTextView.text = tourpackage.nameTourPackage
            tourpackagePriceTextView.text = tourpackage.priceTourPackage
            tourpackageRatingTextView.text = tourpackage.descTourPackage

            // Set rating text if ratingAvgAttraction is not null
            tourpackage.ratingAvgTourPackage?.let { rating ->
                tourpackageRatingTextView.text = rating.toString()
            } ?: run {
                tourpackageRatingTextView.text = ""
            }

            // Load image using Glide if photoAttraction is not null
            tourpackage.photoTourPackage?.let { photo ->
                Glide.with(itemView)
                    .load(photo.toString()) // Convert photo to String if necessary
                    .apply(RequestOptions().placeholder(R.drawable.karimunjawa))
                    .into(tourpackageImageView)
            } ?: run {
                // Set default image if photoAttraction is null
                tourpackageImageView.setImageResource(R.drawable.ic_baseline_image_24)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourPackageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_paket_wisata, parent, false)
        return TourPackageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TourPackageViewHolder, position: Int) {
        val tourpackage = packagetour[position]
        holder.bind(tourpackage)
    }

    override fun getItemCount(): Int {
        return packagetour.size
    }
}
