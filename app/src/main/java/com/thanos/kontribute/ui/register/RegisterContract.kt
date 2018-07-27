package com.thanos.kontribute.ui.register

import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface RegisterContract {

    interface RegisterPresenter: BasePresenter<RegisterView> {
        fun goToLoginActivity()
        fun register()
    }

    interface RegisterView: BaseView {
        fun goToLoginActivity()
        fun goToMainActivity()
    }

}