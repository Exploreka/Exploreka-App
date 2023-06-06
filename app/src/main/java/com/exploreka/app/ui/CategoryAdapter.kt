package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.exploreka.app.data.Category

class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.categoryName)
        private val iconView : ImageView = itemView.findViewById(R.id.categoryIconImageView)

        fun bind(category: Category) {
            nameTextView.text = category.name
            iconView.setImageResource(getIconResource(category.photo))
        }
    }

    private fun getIconResource(categoryId: Int): Int {
        return when (categoryId) {
            1 -> R.drawable.ic_baseline_headphones_24
            2 -> R.drawable.ic_baseline_help_outline_24
            3 -> R.drawable.ic_baseline_home_24
            // Tambahkan kategori lainnya sesuai kebutuhan
            else -> R.drawable.ic_baseline_favorite_24
        }
    }
}
