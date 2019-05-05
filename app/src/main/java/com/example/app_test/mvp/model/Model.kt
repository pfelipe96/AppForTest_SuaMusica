package com.example.app_test.mvp.model

import com.example.app_test.mvp.presenter.PresenterInterface
import com.example.app_test.network.SeatGeekNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import javax.inject.Inject

class Model @Inject constructor(val seatGeekNetwork: SeatGeekNetwork): ModelInterface{

    private lateinit var reference: WeakReference<PresenterInterface>

    private val presenter by lazy {
        reference.get()
    }

    override fun attachedPresenter(presenterInterface: PresenterInterface) {
        reference = WeakReference(presenterInterface)
    }

    override fun setSearch(it: String, page: Int) {
        seatGeekNetwork.getSearchEvents(it, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(page == 1)
                        presenter?.loadData(it)
                    else
                        presenter?.loadMore(it)
                },

                onError = {
                    presenter?.showError(it.message.toString())
                    presenter?.swipeRefresh(false)
                }
            )
    }

}