package com.example.app_test.mvp.presenter

import com.example.app_test.data.SeatGeekData
import com.example.app_test.mvp.model.Model
import com.example.app_test.mvp.ui.MainInterface
import java.lang.ref.WeakReference
import javax.inject.Inject

class Presenter @Inject constructor(val model: Model): PresenterInterface{

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
    }

    override fun loadMore(it: SeatGeekData) {
        view?.loadMore(it)
    }

    override fun setSearch(it: String, page: Int) {
        model.setSearch(it, page)
    }

    override fun showError(it: String) {
        view?.showError(it)
    }

    override fun swipeRefresh(it: Boolean){
        view?.swipeRefresh(it)
    }

}