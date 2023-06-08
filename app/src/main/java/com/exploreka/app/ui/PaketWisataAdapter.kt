package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.exploreka.app.data.PaketWisata

class PaketWisataAdapter(private val paketwisata: List<PaketWisata>) : RecyclerView.Adapter<PaketWisataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paket_wisata , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paketwisata = paketwisata[position]
        holder.bind(paketwisata)
    }

    override fun getItemCount(): Int {
        return paketwisata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_title_paket)
        private val PriceTextView: TextView = itemView.findViewById(R.id.tv_price_paket)
        private val ReviewTextView: TextView = itemView.findViewById(R.id.tv_reviewStar_paket)
        private val JumlahUlasaanTextView: TextView = itemView.findViewById(R.id.jumlah_ulasan_paket)
        private val iconView : ImageView = itemView.findViewById(R.id.img_view_paket)

        fun bind(paketwisata: PaketWisata) {
            nameTextView.text = paketwisata.name
            PriceTextView.text = paketwisata.price
            ReviewTextView.text = paketwisata.review_star
            JumlahUlasaanTextView.text = paketwisata.jumlah_ulasan
            iconView.setImageResource(getIconResource(paketwisata.photo))
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
