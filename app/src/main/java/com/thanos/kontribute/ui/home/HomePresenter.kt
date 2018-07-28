package com.thanos.kontribute.ui.home

import com.amulyakhare.textdrawable.util.ColorGenerator
import com.thanos.kontribute.data.model.Group

class HomePresenter : HomeContract.HomePresenter {

    private var transactionsView: HomeContract.HomeView? = null

    override fun attachView(view: HomeContract.HomeView) {
        this.transactionsView = view
    }

    override fun fetchGroups() {
        transactionsView?.showProgress()
        val groups = arrayListOf(
                Group(title = "FYB Trip",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Trip to Dubai",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Let\'s go to london",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "We gather dey",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Main One",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Germany Straight!",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor)
        )
        transactionsView?.hideProgress()
        transactionsView?.showGroups(groups)
    }

    override fun detachView() {
        this.transactionsView = null
    }
}