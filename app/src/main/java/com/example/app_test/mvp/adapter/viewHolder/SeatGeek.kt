package com.example.app_test.mvp.adapter.viewHolder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.app_test.R
import com.example.app_test.data.EventsData
import com.example.app_test.mvp.adapter.details.DetailsSeatGeek
import com.example.app_test.mvp.interfaces.ItemFavorite
import com.example.app_test.utils.Utils.Companion.adjustTime
import com.example.app_test.utils.Utils.Companion.createOnlyTaxonomies
import com.example.app_test.utils.Utils.Companion.eventsDataToString
import kotlinx.android.synthetic.main.card_view_seat_geek.view.*

class SeatGeek(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val shortTitle = itemView.short_title_card_view
    val date = itemView.announce_date_card_view
    val taxonomies = itemView.taxonomies_card_view
    val favorite = itemView.favorite
    val cardView = itemView.card_view_seat_geek


    fun bind(event: EventsData, itemFavorite: ItemFavorite) {
        shortTitle.text = event.shortTitle
        date.text = adjustTime(event.announceData)


        taxonomies.text = createOnlyTaxonomies(event.taxonomies)

        setFlagFavorite(!event.isFavorite)

        favorite.setOnClickListener {
            val isFavorite = event.isFavorite
            event.isFavorite = !isFavorite

            itemFavorite.onClickFavorite(event)
        }

        cardView.setOnClickListener {
            val intent = Intent(it.context, DetailsSeatGeek::class.java)
            val convertEventToString = eventsDataToString(eventsData = event)
            intent.putExtra("event_data", convertEventToString)

            it.context.startActivity(intent)
        }
    }

    private fun setFlagFavorite(flag: Boolean) {
        if (flag)
            favorite.setImageResource(R.drawable.ic_favorite_grey_24dp)
        else
            favorite.setImageResource(R.drawable.ic_favorite_lilac_24dp)
    }
}