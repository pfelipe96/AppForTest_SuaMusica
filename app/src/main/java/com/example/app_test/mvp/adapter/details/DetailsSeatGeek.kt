package com.example.app_test.mvp.adapter.details

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.app_test.R
import kotlinx.android.synthetic.main.activity_details_seat_geek.*

class DetailsSeatGeek : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_seat_geek)

        val shortTitle = intent.getStringExtra("short_title")
        initialize(shortTitle)

        val titleString = intent.getStringExtra("title")
        title_text_view.text = titleString

        val announceDate = intent.getStringExtra("announce_date")
        announce_date.text = "Anúnciado em $announceDate"

        val url = intent.getStringExtra("url")
        url_text_view.text = "Site: $url"

        val isOpen = intent.getBooleanExtra("is_open", false)
        if(isOpen){
            is_open.text = "Aberto"
        }else{
            is_open.text = "Fechado"
        }

        val taxonomies = intent.getStringExtra("taxanomies")
        taxonomies_text_view.text = "Temas: $taxonomies"

        val averagePrice = intent.getIntExtra("average_price", 0)
        average_price.text = "Preço mínimo: US$averagePrice"

        val lowestPrice = intent.getIntExtra("lowest_price", 0)
        lowest_price.text = "Preço médio: US$lowestPrice"

        val highestPrice = intent.getIntExtra("highest_price", 0)
        highest_price.text = "Preço máximo: US$highestPrice"

        val country = intent.getStringExtra("country")
        country_text_view.text = "País: $country"

        val name = intent.getStringExtra("name")
        name_text_view.text = name

        val state = intent.getStringExtra("state")
        state_text_view.text = "Estado: $state"

        val extendedAddress = intent.getStringExtra("extended_address")
        address_text_view.text = "Endereço: $extendedAddress"

    }

    private fun initialize(shortTitle: String){
        setSupportActionBar(toolbar_seat_geek_detail)

        supportActionBar?.title = shortTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
