package com.example.app_test.mvp.interfaces

import com.example.app_test.data.EventsData

interface ItemFavorite{
    fun onClickFavorite(eventsData: EventsData)
}