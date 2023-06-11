package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.exploreka.app.data.Artikel

class ArtikelAdapter(private val artikel: List<Artikel>) : RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel_inspiratif_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artikel = artikel[position]
        holder.bind(artikel)
    }

    override fun getItemCount(): Int {
        return artikel.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val judulTextView: TextView = itemView.findViewById(R.id.headline_news)
        private val iconView : ImageView = itemView.findViewById(R.id.image_news)

        fun bind(artikel: Artikel) {
            judulTextView.text = artikel.judul
            iconView.setImageResource(getIconResource(artikel.photo))
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
            else -> R.drawable.kepulauan_togian
        }
    }
}