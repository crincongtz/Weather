package com.wizeline.interviews.weather.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.interviews.weather.locked.City
import kotlinx.android.synthetic.main.item_city.view.*

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindToItem(city: City) {
        itemView.city_text.text = city.name
    }
}
