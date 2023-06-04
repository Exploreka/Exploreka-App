package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.exploreka.app.data.Category
import com.exploreka.app.data.Wishlist

class WishlistAdapter(private val wishlist: List<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wishlist = wishlist[position]
        holder.bind(wishlist)
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.text_wisata_name)
        private val priceTextView: TextView = itemView.findViewById(R.id.text_wisata_price)
        private val iconView : ImageView = itemView.findViewById(R.id.image_wishlist)

        fun bind(wishlist: Wishlist) {
            nameTextView.text = wishlist.name
            priceTextView.text = wishlist.price
            iconView.setImageResource(getIconResource(wishlist.photo))
        }
    }

    private fun getIconResource(categoryId: Int): Int {
        return when (categoryId) {
            1 -> R.drawable.karimunjawa
            2 -> R.drawable.kepulauan_togian
            // Tambahkan kategori lainnya sesuai kebutuhan
            else -> R.drawable.karimunjawa
        }
    }

}
