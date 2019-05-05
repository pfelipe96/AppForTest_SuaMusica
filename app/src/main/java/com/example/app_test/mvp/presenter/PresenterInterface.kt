package com.example.app_test.mvp.presenter

import com.example.app_test.data.SeatGeekData
import com.example.app_test.mvp.ui.MainInterface

interface PresenterInterface{
    fun attachedView(it: MainInterface)
    fun loadData(it: SeatGeekData)
    fun showError(it: String)
    fun loadMore(it: SeatGeekData)
    fun setSearch(it: String, page: Int)
    fun swipeRefresh(it: Boolean)
}