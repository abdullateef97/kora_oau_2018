package com.thanos.kontribute.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thanos.kontribute.App
import com.thanos.kontribute.ui.login.LoginActivity
import com.thanos.kontribute.ui.main.MainActivity
import com.thanos.kontribute.R
import com.thanos.kontribute.R.id.btnRegister
import com.thanos.kontribute.R.id.txtLogin
import com.thanos.kontribute.data.model.User
import com.thanos.kontribute.helper.hideProgressDialog
import com.thanos.kontribute.helper.showProgressDialog
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterContract.RegisterView {

    @Inject
    lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        App.getInstance().getAppComponent().inject(this)
        registerPresenter.attachView(this)

        btnRegister.setOnClickListener {
            registerPresenter.register(
                    User("", "${edtFullName.text}",
                            "${edtEmail.text}", "",""),
                    "${edtPassword.text}"
            )
        }

        txtLogin.setOnClickListener {
            registerPresenter.goToLoginActivity()
        }
    }

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        registerPresenter.detachView()
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
}
