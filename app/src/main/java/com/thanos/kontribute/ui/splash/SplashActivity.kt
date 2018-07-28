package com.thanos.kontribute.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.thanos.kontribute.App
import com.thanos.kontribute.ui.main.MainActivity
import com.thanos.kontribute.ui.register.RegisterActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.getInstance().getAppComponent().inject(this)

        if (firebaseAuth.currentUser == null) {
            startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}
