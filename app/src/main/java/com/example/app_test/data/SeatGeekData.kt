package com.example.app_test.data

import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

data class SeatGeekData(
    @SerializedName("events") val events : ArrayList<EventsData> = arrayListOf()
)

data class EventsData(
    @SerializedName("title") val title: String = "",
    @SerializedName("short_title") val shortTitle: String = "",
    @SerializedName("announce_date") val announceData: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("is_open") val isOpen: Boolean = false,
    @SerializedName("stats") val stats: StatsData? = null,
    @SerializedName("taxonomies") val taxonomies: ArrayList<TaxonomiesData> = arrayListOf(),
    @SerializedName("venue") val venue: VenueData? = null,
    var isFavorite: Boolean = false,
    var isLoadMore: Boolean = false
)

data class StatsData(
    @SerializedName("average_price") val averagePrice : Int = 0,
    @SerializedName("lowest_price") val lowestPrice : Int = 0,
    @SerializedName("highest_price") val highestPrice : Int = 0
)

data class TaxonomiesData(
    @SerializedName("name") val name : String = ""
)

data class VenueData(
    @SerializedName("timezone") val timezone: String = "",
    @SerializedName("country") val country: String = "",
    @SerializedName("post_code") val postCode: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("address") val address: String = "",
    @SerializedName("state") val state: String = "",
    @SerializedName("extended_address") val extendedAddress: String = "",
    @SerializedName("display_location") val displayLocation: String = ""
)
