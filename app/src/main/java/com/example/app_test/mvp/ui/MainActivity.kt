package com.example.app_test.mvp.ui

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.example.app_test.MainApplication
import com.example.app_test.R
import com.example.app_test.data.EventsData
import com.example.app_test.data.SeatGeekData
import com.example.app_test.mvp.adapter.SeatGeekAdapter
import com.example.app_test.mvp.adapter.details.DetailsSeatGeek
import com.example.app_test.mvp.interfaces.ItemFavorite
import com.example.app_test.mvp.presenter.Presenter
import com.example.app_test.utils.EndlessRecyclerViewScrollListener
import com.example.app_test.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainInterface, SearchView.OnQueryTextListener, ItemFavorite {

    private var search: SearchView? = null

    @Inject
    lateinit var presenter: Presenter

    private var seatGeekAdapter: SeatGeekAdapter? = null

    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    private var instanceSearch = ""

    private var flagLoadMore = true

    private var totalItem = 10

    private val linearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()
        presenter.attachedView(this)
        initializeEndlessRecyclerView()

        presenter.getListFavorites()

        swipe_refresh_layout.setOnRefreshListener {
            presenter.getListFavorites()
        }

        DetailsSeatGeek.setItemFavorite(this)
    }


    private fun injectDependencies() {
        val mainApplication = application as MainApplication
        mainApplication.applicationComponent.inject(this)
    }

    private fun initializeEndlessRecyclerView() {
        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (page > 0 && flagLoadMore) {
                    if (totalItemsCount >= totalItem) {
                        if (view.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                            val eventsData = EventsData(isLoadMore = true)
                            seatGeekAdapter?.setProgress(eventsData)
                        }

                        presenter.setSearch(instanceSearch, page + 1)
                        flagLoadMore = false
                        totalItem += totalItem
                    }
                }
            }
        }
    }

    override fun onClickFavorite(eventsData: EventsData) {
        presenter.managerFavorite(eventsData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search = menu?.findItem(R.id.search)?.actionView as SearchView
        search?.queryHint = getString(R.string.search_title)
        search?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        search?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true

    override fun onQueryTextSubmit(query: String?): Boolean {
        message_for_user_favorite_empty.visibility = View.GONE

        swipe_refresh_layout.apply {
            visibility = View.VISIBLE
            isRefreshing = true
        }

        query?.let {
            instanceSearch = it
            totalItem = 10
            flagLoadMore = true

            presenter.setSearch(it, 1)
        }

        Utils.hideKeyboardy(this@MainActivity)

        return true
    }

    override fun loadData(it: SeatGeekData) {
        seatGeekAdapter = SeatGeekAdapter(it.events, this)
        seatGeekAdapter?.notifyDataSetChanged()

        linearLayoutManager.orientation = RecyclerView.VERTICAL

        recycler_view?.apply {
            visibility = View.VISIBLE
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = seatGeekAdapter
            addOnScrollListener(endlessRecyclerViewScrollListener)
        }

        swipe_refresh_layout.apply {
            visibility = View.VISIBLE
            isRefreshing = false
        }
    }

    override fun clearAdapter(){
        seatGeekAdapter?.setClearDatabase()
        seatGeekAdapter?.notifyDataSetChanged()
    }

    override fun loadMore(it: SeatGeekData) {
        seatGeekAdapter?.loadMore(it)
        flagLoadMore = true
    }

    override fun swipeRefresh(it: Boolean) {
        swipe_refresh_layout.isRefreshing = it
    }

    override fun showError(it: String) {
        Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
    }

    override fun setFavorite(it: EventsData) {
        seatGeekAdapter?.setFavorite(it)
    }

    override fun setVisibilityForMessage(it: Int){
        message_for_user_favorite_empty.visibility = it
    }

    override fun setVisibilityForSwipe(it: Int){
        swipe_refresh_layout.visibility = it
    }

}
