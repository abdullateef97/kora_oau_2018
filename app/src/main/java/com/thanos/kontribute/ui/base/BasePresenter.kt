package com.thanos.kontribute.ui.base

interface BasePresenter<in I: BaseView> {
    fun attachView(view: I)
    fun detachView()
}