package com.example.app_test.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.collections.ArrayList

data class SeatGeekData(
   @SerializedName("events") val events : ArrayList<EventsData> = arrayListOf()
)

@Entity(tableName = "EventsDataFavorites")
data class EventsData(
    @PrimaryKey
    @ColumnInfo(name ="id") @SerializedName("id") var id: String = "",
    @ColumnInfo(name ="title") @SerializedName("title") var title: String = "",
    @ColumnInfo(name ="short_title") @SerializedName("short_title") var shortTitle: String = "",
    @ColumnInfo(name ="announce_date") @SerializedName("announce_date") var announceData: String = "",
    @ColumnInfo(name ="url") @SerializedName("url") var url: String = "",
    @ColumnInfo(name ="is_open") @SerializedName("is_open") var isOpen: Boolean = false,
    @ColumnInfo(name ="stats") @SerializedName("stats") var stats: StatsData? = StatsData(),
    @ColumnInfo(name ="taxonomies") @SerializedName("taxonomies") var taxonomies: ArrayList<TaxonomiesData> = arrayListOf(),
    @ColumnInfo(name ="venue") @SerializedName("venue") var venue: VenueData? = VenueData(),
    @ColumnInfo(name ="isFavorite") var isFavorite: Boolean = false,
    @ColumnInfo(name ="isLoadMore") var isLoadMore: Boolean = false
) : Serializable

data class StatsData(
    @ColumnInfo(name ="average_price") @SerializedName("average_price") var averagePrice : Int = 0,
    @ColumnInfo(name ="lowest_price") @SerializedName("lowest_price") var lowestPrice : Int = 0,
    @ColumnInfo(name ="highest_price") @SerializedName("highest_price") var highestPrice : Int = 0
)

data class TaxonomiesData(
    @ColumnInfo(name ="name") @SerializedName("name") var name : String = ""
)

data class VenueData(
    @ColumnInfo(name ="country") @SerializedName("country") var country: String = "",
    @ColumnInfo(name ="name") @SerializedName("name") var name: String = "",
    @ColumnInfo(name ="address") @SerializedName("address") var address: String = "",
    @ColumnInfo(name ="state") @SerializedName("state") var state: String = "",
    @ColumnInfo(name ="extended_address") @SerializedName("extended_address") var extendedAddress: String = "",
    @ColumnInfo(name ="display_location") @SerializedName("display_location") var displayLocation: String = ""
)
