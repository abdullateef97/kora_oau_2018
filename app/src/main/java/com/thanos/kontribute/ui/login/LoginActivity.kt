package com.thanos.kontribute.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thanos.kontribute.App
import com.thanos.kontribute.ui.main.MainActivity
import com.thanos.kontribute.R
import com.thanos.kontribute.R.id.btnLogin
import com.thanos.kontribute.helper.hideProgressDialog
import com.thanos.kontribute.helper.showProgressDialog
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {

    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.getInstance().getAppComponent().inject(this)
        loginPresenter.attachView(this)

        btnLogin.setOnClickListener {
            loginPresenter.login("${edtEmail.text}", "${edtPassword.text}")
        }
    }

    override fun showProgress() {
        showProgressDialog()
    }

    override fun hideProgress() {
        hideProgressDialog()
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.detachView()
    }
}
