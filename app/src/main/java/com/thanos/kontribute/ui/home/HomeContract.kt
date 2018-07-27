package com.thanos.kontribute.ui.home

import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface HomeContract {

    interface HomePresenter: BasePresenter<HomeView> {
    }

    interface HomeView: BaseView {
        fun showProgress()
        fun hideProgress()
    }
}