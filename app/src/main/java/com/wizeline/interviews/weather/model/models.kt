package com.wizeline.interviews.weather.model

import com.wizeline.interviews.weather.locked.City

sealed class GroupedCitiesList

data class HeaderCountryItem(
   val country: String
) : GroupedCitiesList()

data class ItemCityItem(
   val city: City
) : GroupedCitiesList()
