package com.wizeline.interviews.weather.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_country.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindToHeader(country: String) {
        itemView.country_text.text = country
    }
}
