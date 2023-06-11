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

    private var onItemClickListener: ((Category) -> Unit)? = null

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

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val category = categories[position]
                    onItemClickListener?.invoke(category)
                }
            }
        }

        fun bind(category: Category) {
            nameTextView.text = category.name
            iconView.setImageResource(getIconResource(category.photo))
        }
    }

    private fun getIconResource(categoryId: Int): Int {
        return when (categoryId) {
            1 -> R.drawable.kategori_budaya
            2 -> R.drawable.kategori_taman_hiburan
            3 -> R.drawable.kategori_cagar_alam
            4 -> R.drawable.kategori_bahari
            5 -> R.drawable.kategori_pusat_perbelanjaan
            6 -> R.drawable.kategori_tempat_ibadah
            else -> R.drawable.kategori_lain
        }
    }

    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }
}
