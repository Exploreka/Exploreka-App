package com.exploreka.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class SlideAdapter(private val items: List<OnboardingItem>) : PagerAdapter() {

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_onboarding, container, false)

        val item = items[position]
        view.findViewById<ImageView>(R.id.item_imageSlide).setImageResource(item.imageRes)
        view.findViewById<TextView>(R.id.item_titleSLide).text = item.title
        view.findViewById<TextView>(R.id.item_descSlide).text = item.description

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}