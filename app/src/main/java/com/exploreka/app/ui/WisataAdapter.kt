package com.exploreka.app.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.DetailWisataActivity
import com.exploreka.app.R
import com.exploreka.app.data.Wisata


class WisataAdapter(private val wisata: List<Wisata>) : RecyclerView.Adapter<WisataAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_destinasi_populer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wisataItem = wisata[position]
        holder.bind(wisataItem)
    }

    override fun getItemCount(): Int {
        return wisata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_title_item)
        private val locationTextView: TextView = itemView.findViewById(R.id.tv_location_item)
        private val reviewTextView: TextView = itemView.findViewById(R.id.tv_reviewStar_item)
        private val jumlahUlasanTextView: TextView = itemView.findViewById(R.id.jumlah_ulasan)
        private val iconView: ImageView = itemView.findViewById(R.id.img_view_item)

        fun bind(wisataItem: Wisata) {
            nameTextView.text = wisataItem.name
            locationTextView.text = wisataItem.location
            reviewTextView.text = wisataItem.review_star
            jumlahUlasanTextView.text = wisataItem.jumlah_ulasan
            iconView.setImageResource(getIconResource(wisataItem.photo))

            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(wisataItem)
            }
        }
    }

    private fun getIconResource(categoryId: Int): Int {
        return when (categoryId) {
            1 -> R.drawable.karimunjawa
            2 -> R.drawable.kepulauan_togian
            3 -> R.drawable.karimunjawa
            4 -> R.drawable.kepulauan_togian
            5 -> R.drawable.karimunjawa
            6 -> R.drawable.kepulauan_togian
            7 -> R.drawable.karimunjawa
            8 -> R.drawable.kepulauan_togian
            9 -> R.drawable.karimunjawa
            10 -> R.drawable.kepulauan_togian
            // Tambahkan kategori lainnya sesuai kebutuhan
            else -> R.drawable.karimunjawa
        }
    }

    interface OnItemClickListener {
        fun onItemClick(wisata: Wisata)
    }
}
