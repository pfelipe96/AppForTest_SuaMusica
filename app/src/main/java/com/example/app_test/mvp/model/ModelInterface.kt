package com.example.app_test.mvp.model

import com.example.app_test.mvp.presenter.PresenterInterface

interface ModelInterface{
    fun attachedPresenter(presenterInterface: PresenterInterface)
    fun setSearch(it: String, page: Int)}