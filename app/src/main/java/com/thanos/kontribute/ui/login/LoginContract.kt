package com.thanos.kontribute.ui.login

import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface LoginContract {

    interface LoginPresenter: BasePresenter<LoginView> {
        fun login(email: String, password: String)
    }

    interface LoginView: BaseView {
        fun goToMainActivity()
        fun showProgress()
        fun hideProgress()
        fun showMessage(message: String)
    }

}