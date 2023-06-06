package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.exploreka.app.data.Wisata


class WisataAdapter(private val wisata: List<Wisata>) : RecyclerView.Adapter<WisataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destinasi_populer , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wisata = wisata[position]
        holder.bind(wisata)
    }

    override fun getItemCount(): Int {
        return wisata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_title_item)
        private val LocationTextView: TextView = itemView.findViewById(R.id.tv_location_item)
        private val ReviewTextView: TextView = itemView.findViewById(R.id.tv_reviewStar_item)
        private val JumlahUlasaanTextView: TextView = itemView.findViewById(R.id.jumlah_ulasan)
        private val iconView : ImageView = itemView.findViewById(R.id.img_view_item)

        fun bind(wisata: Wisata) {
            nameTextView.text = wisata.name
            LocationTextView.text = wisata.location
            ReviewTextView.text = wisata.review_star
            JumlahUlasaanTextView.text = wisata.jumlah_ulasan
            iconView.setImageResource(getIconResource(wisata.photo))
        }
    }

    private fun getIconResource(categoryId: Int): Int {
        return when (categoryId) {
            1 -> R.drawable.karimunjawa
            2 -> R.drawable.kepulauan_togian
            3 -> R.drawable.karimunjawa
            4 -> R.drawable.kepulauan_togian
            // Tambahkan kategori lainnya sesuai kebutuhan
            else -> R.drawable.karimunjawa
        }
    }

}
