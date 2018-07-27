package com.thanos.kontribute.ui.home

import com.thanos.kontribute.ui.home.HomeContract
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class HomePresenter() : HomeContract.HomePresenter {

    private var homeView: HomeContract.HomeView? = null

    override fun attachView(view: HomeContract.HomeView) {
        this.homeView = view
    }

    override fun detachView() {
        this.homeView = null
    }
}