package com.thanos.kontribute.ui.home

import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface HomeContract {

    interface HomePresenter: BasePresenter<HomeView> {
        fun fetchGroups()
    }

    interface HomeView: BaseView {
        fun showProgress()
        fun hideProgress()
        fun showGroups(groups: ArrayList<Group>)
        fun showMessage(message: String)
    }
}