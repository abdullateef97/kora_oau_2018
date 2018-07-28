package com.thanos.kontribute.ui.withdrawal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import kotlinx.android.synthetic.main.activity_withdrawal.*

class WithdrawalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Withdraw"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        App.getInstance().getAppComponent().inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
