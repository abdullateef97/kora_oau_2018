package com.thanos.kontribute.ui.login

import com.atlascc.kontribute.data.remote.ApiService

class LoginPresenter(apiService: ApiService) : LoginContract.LoginPresenter {
    private var loginView: LoginContract.LoginView? = null

    override fun login() {
        loginView?.goToMainActivity()
    }

    override fun attachView(view: LoginContract.LoginView) {
        this.loginView = view
    }

    override fun detachView() {
        this.loginView = null
    }
}