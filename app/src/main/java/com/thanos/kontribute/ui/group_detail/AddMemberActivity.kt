package com.thanos.kontribute.ui.group_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import kotlinx.android.synthetic.main.activity_add_member.*

class AddMemberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Add Member"
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
