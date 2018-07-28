package com.thanos.kontribute.ui.create_group

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.helper.BUNDLE_NEW_GROUP
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_create_group.*
import javax.inject.Inject

class CreateGroupActivity : AppCompatActivity(), CreateGroupContract.CreateGroupView {

    @Inject
    lateinit var createGroupPresenter: CreateGroupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Create Group"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        App.getInstance().getAppComponent().inject(this)
        createGroupPresenter.attachView(this)

        btnCreate.setOnClickListener {
            // THESE WILL GO TO PRESENTER
            if (edtTitle.text.isEmpty() || edtDescription.text.isEmpty()) {
                showToast("Insert all the required fields")
            } else {
                val group = Group(
                        title = edtTitle.text.trim().toString(),
                        description = edtDescription.text.trim().toString(),
                        color = ColorGenerator.MATERIAL.randomColor
                )
                setResult(Activity.RESULT_OK, Intent().putExtra(BUNDLE_NEW_GROUP, group))
                finish()
            }
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

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

    override fun onDestroy() {
        super.onDestroy()
        createGroupPresenter.detachView()
    }
}
