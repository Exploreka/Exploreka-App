package com.exploreka.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.R
import com.google.android.material.chip.Chip

class CityAdapter(private val cities: List<String>) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private var selectedItemPosition = RecyclerView.NO_POSITION

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chipCity: Chip = itemView.findViewById(R.id.chip_city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheet_city, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.chipCity.text = city

        holder.chipCity.isChecked = position == selectedItemPosition
        holder.chipCity.setOnClickListener {
            setSelectedItem(position)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    private fun setSelectedItem(position: Int) {
        if (selectedItemPosition != position) {
            val previousSelectedPosition = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedItemPosition)
        }
    }

    fun getSelectedItem(): String? {
        return if (selectedItemPosition != RecyclerView.NO_POSITION) {
            cities[selectedItemPosition]
        } else {
            null
        }
    }
}