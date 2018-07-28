package com.thanos.kontribute.ui.group_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.firebase.firestore.FirebaseFirestore
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.data.model.User
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_add_member.*
import javax.inject.Inject

class AddMemberActivity : AppCompatActivity() {

    @Inject
    lateinit var firestore: FirebaseFirestore

    private lateinit var group: Group


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Add Member"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        group = intent?.getParcelableExtra("group")!!
        App.getInstance().getAppComponent().inject(this)

        btnAddMember.setOnClickListener {
//            fetchUserWithEmail(edtEmail.text?.trim().toString())
            showToast("New Member Added")
            finish()
        }
    }

    private fun fetchUserWithEmail(email: String) {
        firestore.collection("users")
                .whereEqualTo("email", email)
                .limit(1)
                .get()
                .addOnSuccessListener {
                    val user = it.toObjects(User::class.java)[0]
                    addMember(user)
                }
    }

    private fun addMember(user: User) {
        firestore.collection("group")
                .document(group.id)
                .collection("members")
                .add(user)
                .addOnSuccessListener {
                    showToast("New Member Added")
                    finish()
                }
                .addOnFailureListener {
                    showToast("Adding Member Failed: " + it.localizedMessage)
                }
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
