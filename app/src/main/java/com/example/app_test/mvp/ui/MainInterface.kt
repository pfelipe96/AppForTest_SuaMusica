package com.example.app_test.mvp.ui

import com.example.app_test.data.SeatGeekData

interface MainInterface{
    fun loadData(it: SeatGeekData)
    fun showError(it: String)
    fun loadMore(it: SeatGeekData)
    fun swipeRefresh(it: Boolean)
}
