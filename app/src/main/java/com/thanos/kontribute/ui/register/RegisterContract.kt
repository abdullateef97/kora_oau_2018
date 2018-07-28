package com.thanos.kontribute.ui.register

import com.thanos.kontribute.data.model.User
import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface RegisterContract {

    interface RegisterPresenter: BasePresenter<RegisterView> {
        fun goToLoginActivity()
        fun register(user: User, password: String)
    }

    interface RegisterView: BaseView {
        fun showProgress()
        fun hideProgress()
        fun goToLoginActivity()
        fun goToMainActivity()
        fun showMessage(message: String)
    }

}