package com.thanos.kontribute.ui.home

import com.amulyakhare.textdrawable.util.ColorGenerator
import com.thanos.kontribute.data.model.Group

class HomePresenter() : HomeContract.HomePresenter {

    private var homeView: HomeContract.HomeView? = null

    override fun attachView(view: HomeContract.HomeView) {
        this.homeView = view
    }

    override fun fetchGroups() {
        homeView?.showProgress()
        val groups = arrayListOf(
                Group(title = "Awa Ajo",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Trip to Dubai",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Let\'s go to london",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "We gather dey",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Main One",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Germany Straight",description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor)
        )
        homeView?.hideProgress()
        homeView?.showGroups(groups)
    }

    override fun detachView() {
        this.homeView = null
    }
}