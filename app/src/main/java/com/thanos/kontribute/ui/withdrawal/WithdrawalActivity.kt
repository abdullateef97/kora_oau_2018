package com.thanos.kontribute.ui.withdrawal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_withdrawal.*
import javax.inject.Inject

class WithdrawalActivity : AppCompatActivity() {

    @Inject
    lateinit var firestore: FirebaseFirestore

    private lateinit var group: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Withdraw"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        group = intent?.getParcelableExtra("group")!!

        App.getInstance().getAppComponent().inject(this)

        spnMember.adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayListOf(
                "Quadri Anifowose", "Lateef Adeniran", "Lanre Teriba",
                "Mayomi Ayandiran", "Rasheed Sulayman", "Emmanuel Kehinde"
        ))

        btnWithdraw.setOnClickListener {
            if (edtAmount.text.toString().isEmpty()) {
                showToast("Insert the amount to withdraw")
                return@setOnClickListener
            }
            showToast("Withdrawal Successful")
            finish()
//            withDrawFromWallet(edtAmount.text?.trim().toString())
        }
    }

    private fun withDrawFromWallet(amount: String) {
        firestore.collection("group")
                .document(group.id)
                .update("balance", (group.balance - amount.toInt()))
                .addOnSuccessListener {
                    showToast("Withdrawal Successful")
                    finish()
                }
                .addOnFailureListener {
                    showToast("Withdrawal Failed: " + it.localizedMessage)
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
