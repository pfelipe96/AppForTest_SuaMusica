package com.example.app_test.utils

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils
import android.view.inputmethod.InputMethodManager
import com.example.app_test.data.EventsData
import com.example.app_test.data.TaxonomiesData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Utils{
    companion object{
        fun hideKeyboardy(context: Activity){
            val view = context.currentFocus
            if (view != null) {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        fun adjustTime(it: String): CharSequence {
            var convert: CharSequence? = null
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            try {
                val mDate = sdf.parse(it)
                val timeInMilliseconds = mDate.time
                convert = DateUtils.getRelativeTimeSpanString(timeInMilliseconds)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return convert ?: ""
        }

        fun createOnlyTaxonomies(taxonomies: ArrayList<TaxonomiesData>) : String{
            var names = ""

            taxonomies.forEachIndexed { index, taxonomiesData ->
                names += taxonomiesData.name
                if (index != taxonomies.size - 1) {
                    names += " * "
                }
            }

            return names
        }

        fun eventsDataToString(eventsData: EventsData?): String {
            return if (eventsData != null) {
                val gson = Gson()
                gson.toJson(eventsData) ?: ""
            } else {
                ""
            }
        }

        fun stringToEventsData(it: String?): EventsData {
            return if(it != null) {
                val listType = object : TypeToken<EventsData>() {}.type
                Gson().fromJson<EventsData>(it, listType) ?: EventsData()
            }else{
                EventsData()
            }
        }
    }
}