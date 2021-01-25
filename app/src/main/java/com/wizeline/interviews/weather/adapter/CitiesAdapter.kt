package com.wizeline.interviews.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.interviews.weather.R
import com.wizeline.interviews.weather.locked.City
import com.wizeline.interviews.weather.model.GroupedCitiesList
import com.wizeline.interviews.weather.model.HeaderCountryItem
import com.wizeline.interviews.weather.model.ItemCityItem

private const val HEADER_COUNTRY = 0
private const val ITEM_CITY = 1

class CitiesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var countryItemList: List<GroupedCitiesList>

    fun setCountryCityData(cityList: List<City>) {
        require(cityList.isNotEmpty())
        this.countryItemList = createGroupedCitiesList(cityList)
        notifyDataSetChanged()
    }

    private fun createGroupedCitiesList(cityList: List<City>): List<GroupedCitiesList> {
        val result = mutableListOf<GroupedCitiesList>()
        var country = cityList[0].countryName
        // add first header
        result.add(HeaderCountryItem(country))
        for (city in cityList.withIndex()) {
            result.add(ItemCityItem(city.value))
            // add new header if next country nae is different
            if (city.index + 1 < cityList.size) {
                val nextCountry = cityList[city.index + 1].countryName
                if (country != nextCountry) {
                    country = nextCountry
                    result.add(
                        HeaderCountryItem(nextCountry)
                    )
                }
            }
        }
        return result
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER_COUNTRY -> HeaderViewHolder(layoutInflater.inflate(R.layout.item_country, parent, false))
            ITEM_CITY -> ItemViewHolder(layoutInflater.inflate(R.layout.item_city, parent, false))
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = countryItemList[position]
        when (getItemViewType(position)) {
            HEADER_COUNTRY -> {
                require(holder is HeaderViewHolder) { "RecyclerAdapter returning wrong view holder type" }
                holder.bindToHeader((item as HeaderCountryItem).country)
            }
            ITEM_CITY -> {
                require(holder is ItemViewHolder) { "RecyclerAdapter returning wrong view holder type" }
                holder.bindToItem((item as ItemCityItem).city)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (::countryItemList.isInitialized) {
            countryItemList.size
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (countryItemList[position]) {
            is HeaderCountryItem -> HEADER_COUNTRY
            is ItemCityItem -> ITEM_CITY
        }
    }
}
