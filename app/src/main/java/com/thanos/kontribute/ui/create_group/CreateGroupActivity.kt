package com.thanos.kontribute.ui.create_group

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.data.model.Member
import com.thanos.kontribute.helper.BUNDLE_NEW_GROUP
import com.thanos.kontribute.helper.hideProgressDialog
import com.thanos.kontribute.helper.showProgressDialog
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_create_group.*
import javax.inject.Inject


class CreateGroupActivity : AppCompatActivity(), CreateGroupContract.CreateGroupView {

    @Inject
    lateinit var createGroupPresenter: CreateGroupPresenter

    @Inject
    lateinit var firestore: FirebaseFirestore

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

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
                showToast("Insert all the necessary fields")
            } else {
                val group = Group(
                        title = edtTitle.text.trim().toString(),
                        description = edtDescription.text.trim().toString(),
                        color = ColorGenerator.MATERIAL.randomColor,
                        balance = 0,
                        members = arrayListOf(
                                firebaseAuth.currentUser?.uid?.let { it1 -> Member(it1,"Test user", "", true) }
                        )
                )
//                saveGroup(group)

                setResult(Activity.RESULT_OK, Intent().putExtra(BUNDLE_NEW_GROUP, group))
                showToast("Member Added")
                finish()
            }
        }
    }

    private fun saveGroup(hash: HashMap<String, String>) {
        showProgressDialog()
        val id = firestore.collection("groups")
                .document().id
        hash["id"] = id
        firestore.collection("groups")
                .document(id)
                .set(hash as Map<String, Any>)
                .addOnSuccessListener {
                    documentReference ->

                    addMember(id)
                }
                .addOnFailureListener {
                    exception ->
                    hideProgressDialog()
                    showToast("Group creation failed: " + exception.localizedMessage)
                }
    }

    private fun addMember(id: String) {
        firestore.collection("groups")
                .document(id)
                .collection("members")
                .document(firebaseAuth.currentUser?.uid!!)
                .set(Member(firebaseAuth.currentUser?.uid!!,"Test User", "", true))
                .addOnSuccessListener {
                    documentReference ->
                    hideProgressDialog()
                    showToast("Group created successfully")
                    finish()
                }
                .addOnFailureListener {
                    exception ->
                    hideProgressDialog()
                    showToast("Group creation failed: " + exception.localizedMessage)
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
