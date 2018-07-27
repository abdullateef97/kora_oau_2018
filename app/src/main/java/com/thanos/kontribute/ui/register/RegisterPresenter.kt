package com.thanos.kontribute.ui.register

import com.atlascc.kontribute.data.remote.ApiService
import com.thanos.kontribute.ui.register.RegisterContract

class RegisterPresenter(apiService: ApiService) : RegisterContract.RegisterPresenter {
    private var registerView: RegisterContract.RegisterView? = null

    override fun register() {
        registerView?.goToMainActivity()
    }

    override fun goToLoginActivity() {
        registerView?.goToLoginActivity()
    }

    override fun attachView(view: RegisterContract.RegisterView) {
        this.registerView = view
    }

    override fun detachView() {
        this.registerView = null
    }
}