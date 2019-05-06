package com.example.app_test.mvp.adapter.details

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.app_test.R
import com.example.app_test.data.EventsData
import com.example.app_test.mvp.interfaces.ItemFavorite
import com.example.app_test.utils.Utils.Companion.adjustTime
import com.example.app_test.utils.Utils.Companion.createOnlyTaxonomies
import com.example.app_test.utils.Utils.Companion.stringToEventsData
import kotlinx.android.synthetic.main.activity_details_seat_geek.*

class DetailsSeatGeek : AppCompatActivity() {

    private var eventsData = EventsData()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_seat_geek)

        val eventsAsString = intent.getStringExtra("event_data")
        eventsData = stringToEventsData(eventsAsString)

        initialize(eventsData.shortTitle)

        title_text_view.text = eventsData.title
        announce_date.text = "Anúnciado em ${adjustTime(eventsData.announceData)}"
        url_text_view.text = "Site: ${eventsData.url}"

        if (eventsData.isOpen) {
            is_open.text = getString(R.string.opened)
        } else {
            is_open.text = getString(R.string.closed)
        }

        taxonomies_text_view.text = "Temas: ${createOnlyTaxonomies(eventsData.taxonomies)}"
        average_price.text = "Preço médio: US${eventsData.stats?.averagePrice}"
        lowest_price.text = "Preço mínimo: US${eventsData.stats?.lowestPrice}"
        highest_price.text = "Preço máximo: US${eventsData.stats?.highestPrice}"

        country_text_view.text = "País: ${eventsData.venue?.country}"
        name_text_view.text = eventsData.venue?.name
        state_text_view.text = "Estado: ${eventsData.venue?.state}"
        address_text_view.text = "Endereço: ${eventsData.venue?.extendedAddress}"
    }


    private fun initialize(shortTitle: String) {
        setSupportActionBar(toolbar_seat_geek_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = shortTitle
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_seat_geek, menu)

        val iconFavorite = menu?.findItem(R.id.favorite_menu) as MenuItem

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (eventsData.isFavorite)
                iconFavorite.icon = getDrawable(R.drawable.ic_favorite_lilac_24dp)
            else
                iconFavorite.icon = getDrawable(R.drawable.ic_favorite_grey_24dp)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.favorite_menu -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (eventsData.isFavorite) {
                        item.icon = getDrawable(R.drawable.ic_favorite_grey_24dp)
                        eventsData.isFavorite = false
                        sItemFavorite.onClickFavorite(eventsData)
                    } else {
                        item.icon = getDrawable(R.drawable.ic_favorite_lilac_24dp)
                        eventsData.isFavorite = true
                        sItemFavorite.onClickFavorite(eventsData)
                    }
                }
            }
        }

        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        @JvmStatic
        lateinit var sItemFavorite: ItemFavorite

        @JvmStatic
        fun setItemFavorite(it: ItemFavorite) {
            sItemFavorite = it
        }
    }

}
