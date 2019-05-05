package com.example.app_test.mvp.adapter.viewHolder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import com.example.app_test.R
import com.example.app_test.data.EventsData
import com.example.app_test.mvp.adapter.details.DetailsSeatGeek
import kotlinx.android.synthetic.main.card_view_seat_geek.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SeatGeek(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val shortTitle = itemView.short_title_card_view
    val date = itemView.announce_date_card_view
    val taxonomies = itemView.taxonomies_card_view
    val favorite = itemView.favorite
    val cardView = itemView.card_view_seat_geek

    fun bind(event: EventsData) {
        shortTitle.text = event.shortTitle
        date.text = ajustTime(event.announceData)

        var names = ""
        event.taxonomies.forEachIndexed { index, taxonomiesData ->
            names += taxonomiesData.name
            if (index != event.taxonomies.size - 1) {
                names += " * "
            }
        }

        taxonomies.text = names

        setFlagFavorite(!event.isFavorite)

        favorite.setOnClickListener {
            setFlagFavorite(event.isFavorite)
        }

        cardView.setOnClickListener {
            val intent = Intent(it.context, DetailsSeatGeek::class.java)
            intent.putExtras(setPrepareIntent(event, names))
            it.context.startActivity(intent)
        }
    }

    private fun setFlagFavorite(flag: Boolean) {
        if (flag)
            favorite.setImageResource(R.drawable.ic_favorite_grey_24dp)
        else
            favorite.setImageResource(R.drawable.ic_favorite_lilac_24dp)
    }

    private fun setPrepareIntent(event: EventsData, taxonomies: String): Intent {
        val intent = Intent()

        intent.putExtra("title", event.title)
        intent.putExtra("short_title", event.shortTitle)
        intent.putExtra("announce_date", ajustTime(event.announceData))
        intent.putExtra("url", event.url)
        intent.putExtra("is_open", event.isOpen)
        intent.putExtra("taxanomies", taxonomies)

        intent.putExtra("average_price", event.stats?.averagePrice)
        intent.putExtra("lowest_price", event.stats?.lowestPrice)
        intent.putExtra("highest_price", event.stats?.highestPrice)

        intent.putExtra("country", event.venue?.country)
        intent.putExtra("name", event.venue?.name)
        intent.putExtra("state", event.venue?.state)
        intent.putExtra("extended_address", event.venue?.extendedAddress)

        return intent
    }

    private fun ajustTime(it: String): CharSequence? {
        var convert: CharSequence? = null
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        try {
            val mDate = sdf.parse(it)
            val timeInMilliseconds = mDate.time
            convert = DateUtils.getRelativeTimeSpanString(timeInMilliseconds)
        }catch (e: ParseException){
            e.printStackTrace()
        }

        return convert
    }

}