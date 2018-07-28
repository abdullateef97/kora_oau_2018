package com.thanos.kontribute.ui.login

import com.google.firebase.auth.FirebaseAuth
import com.thanos.kontribute.helper.isValidEmail
import com.thanos.kontribute.helper.isValidPassword

class LoginPresenter(private var firebaseAuth: FirebaseAuth): LoginContract.LoginPresenter {

    private var loginView: LoginContract.LoginView? = null

    override fun login(email: String, password: String) {
        if (!email.isValidEmail()) {
            loginView?.showMessage("Enter a valid email.")
            return
        }

        if (!password.isValidPassword()) {
            loginView?.showMessage("Enter a valid password.")
            return
        }
        loginView?.showProgress()

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { result ->
                    loginView?.hideProgress()
                    if (result.isSuccessful) {
                        loginView?.goToMainActivity()
                    } else {
                        loginView?.showMessage("Error logging in, try again.")
                    }
                }
    }

    override fun attachView(view: LoginContract.LoginView) {
        this.loginView = view
    }

    override fun detachView() {
        this.loginView = null
    }
}