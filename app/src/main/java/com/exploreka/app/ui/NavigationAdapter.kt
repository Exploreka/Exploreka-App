package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.exploreka.app.data.Explore


class NavigationAdapter(private val navigation: List<Explore>) : RecyclerView.Adapter<NavigationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_navigation , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val navigation = navigation[position]
        holder.bind(navigation)
    }

    override fun getItemCount(): Int {
        return navigation.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_title_navigation)
        private val ReviewTextView: TextView = itemView.findViewById(R.id.tv_reviewStar_navigation)
        private val iconView : ImageView = itemView.findViewById(R.id.imageview_navigation)

        fun bind(navigation: Explore) {
            nameTextView.text = navigation.name
            ReviewTextView.text = navigation.review_star
            iconView.setImageResource(getIconResource(navigation.photo))
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