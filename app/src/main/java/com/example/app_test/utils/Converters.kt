package com.example.app_test.utils

import android.arch.persistence.room.TypeConverter
import com.example.app_test.data.EventsData
import com.example.app_test.data.StatsData
import com.example.app_test.data.TaxonomiesData
import com.example.app_test.data.VenueData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        return if(value != null) {
            val listType = object : TypeToken<ArrayList<String>>() {}.type
            Gson().fromJson<ArrayList<String>>(value, listType) ?: arrayListOf()
        }else{
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>?): String {
        return if(list != null) {
            val gson = Gson()
            gson.toJson(list) ?: ""
        }else{
            ""
        }
    }


    // EventsData
    @TypeConverter
    fun EventsDataToString(eventsData: EventsData?): String {
        return if (eventsData != null) {
            val gson = Gson()
            gson.toJson(eventsData) ?: ""
        } else {
            ""
        }
    }

    @TypeConverter
    fun StringToEventsData(it: String?): EventsData {
        return if(it != null) {
            val listType = object : TypeToken<EventsData>() {}.type
            Gson().fromJson<EventsData>(it, listType) ?: EventsData()
        }else{
            EventsData()
        }
    }

    // TaxonomiesData
    @TypeConverter
    fun TaxonomiesDataToString(taxonomiesData: TaxonomiesData?): String {
        return if (taxonomiesData != null) {
            val gson = Gson()
            gson.toJson(taxonomiesData) ?: ""
        } else {
            ""
        }
    }

    @TypeConverter
    fun StringToTaxonomiesData(it: String?): TaxonomiesData {
        return if(it != null) {
            val listType = object : TypeToken<TaxonomiesData>() {}.type
            Gson().fromJson<TaxonomiesData>(it, listType) ?: TaxonomiesData()
        }else{
            TaxonomiesData()
        }
    }

    // ListTaxonomiesData
    @TypeConverter
    fun ListTaxonomiesDataToString(taxonomiesData: ArrayList<TaxonomiesData>?): String {
        return if (taxonomiesData != null) {
            val gson = Gson()
            gson.toJson(taxonomiesData) ?: ""
        } else {
            ""
        }
    }

    @TypeConverter
    fun StringToListTaxonomiesData(it: String?): ArrayList<TaxonomiesData> {
        return if(it != null) {
            val listType = object : TypeToken<ArrayList<TaxonomiesData>>() {}.type
            Gson().fromJson<ArrayList<TaxonomiesData>>(it, listType) ?: arrayListOf(TaxonomiesData())
        }else{
            arrayListOf(TaxonomiesData())
        }
    }

    // StatsData
    @TypeConverter
    fun StatsDataToString(statsData: StatsData?): String {
        return if (statsData != null) {
            val gson = Gson()
            gson.toJson(statsData) ?: ""
        } else {
            ""
        }
    }

    @TypeConverter
    fun StringToStatsData(it: String?): StatsData {
        return if(it != null) {
            val listType = object : TypeToken<StatsData>() {}.type
            Gson().fromJson<StatsData>(it, listType) ?: StatsData()
        }else{
            StatsData()
        }
    }

    // VenueData
    @TypeConverter
    fun VenueDataToString(venueData: VenueData?): String {
        return if (venueData != null) {
            val gson = Gson()
            gson.toJson(venueData) ?: ""
        } else {
            ""
        }
    }

    @TypeConverter
    fun StringToVenueDataa(it: String?): VenueData {
        return if(it != null) {
            val listType = object : TypeToken<VenueData>() {}.type
            Gson().fromJson<VenueData>(it, listType) ?: VenueData()
        }else{
            VenueData()
        }
    }
}