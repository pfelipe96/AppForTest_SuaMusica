package com.example.app_test.mvp.presenter

import android.view.View
import com.example.app_test.data.EventsData
import com.example.app_test.data.SeatGeekData
import com.example.app_test.mvp.model.Model
import com.example.app_test.mvp.room.FavoriteDatabase
import com.example.app_test.mvp.ui.MainInterface
import java.lang.Exception
import java.lang.ref.WeakReference
import javax.inject.Inject

class Presenter @Inject constructor(
    val model: Model,
    val favoriteDatabase: FavoriteDatabase) : PresenterInterface {

    init {
        model.attachedPresenter(this)
    }

    private lateinit var reference: WeakReference<MainInterface>

    private val view by lazy {
        reference.get()
    }

    override fun attachedView(it: MainInterface) {
        reference = WeakReference(it)
    }

    override fun loadData(it: SeatGeekData) {
        view?.loadData(it)
        matchDatabaseWithRequest(it)
    }

    override fun loadMore(it: SeatGeekData) {
        view?.loadMore(it)
        matchDatabaseWithRequest(it)
    }

    override fun setSearch(it: String, page: Int) {
        model.setSearch(it, page)
    }

    override fun showError(it: String) {
        view?.showError(it)
    }

    override fun swipeRefresh(it: Boolean) {
        view?.swipeRefresh(it)
    }

    override fun managerFavorite(eventsData: EventsData) {
        if (eventsData.isFavorite) {
            favoriteDatabase.controlDatabaseDAO().insertFavorite(eventsData)
        } else {
            favoriteDatabase.controlDatabaseDAO().deleteOnlyFavorite(eventsData.id)
        }

        view?.setFavorite(eventsData)
    }

    private fun matchDatabaseWithRequest(it: SeatGeekData) {
        it.events.forEach {
            try {
                val eventsData = favoriteDatabase.controlDatabaseDAO().getOnlyFavorite(it.id)
                if(it.id == eventsData.id)
                    view?.setFavorite(eventsData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getListFavorites() {
        view?.swipeRefresh(true)
        try {
            val events = favoriteDatabase.controlDatabaseDAO().getAllFavorites()

            if(events.isEmpty()) {
                view?.setVisibilityForSwipe(View.GONE)
                view?.swipeRefresh(false)
                view?.clearAdapter()
                view?.setVisibilityForMessage(View.VISIBLE)
            }else {
                view?.setVisibilityForMessage(View.GONE)
                view?.loadData(SeatGeekData(events = events as ArrayList<EventsData>))
            }

        } catch (e: Exception) {
            e.printStackTrace()

            view?.setVisibilityForSwipe(View.GONE)
            view?.swipeRefresh(false)
            view?.clearAdapter()
            view?.setVisibilityForMessage(View.VISIBLE)
        }
    }
}