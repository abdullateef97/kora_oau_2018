package com.thanos.kontribute.ui.deposit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.Transaction
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.atlascc.kontribute.data.remote.ApiService
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.remote.response.ConfirmTransactionResp
import com.thanos.kontribute.data.remote.response.InitializeTransactionResp
import com.thanos.kontribute.helper.SharedPrefHelper
import com.thanos.kontribute.helper.hideProgressDialog
import com.thanos.kontribute.helper.showProgressDialog
import com.thanos.kontribute.helper.showToast
import kotlinx.android.synthetic.main.activity_deposit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DepositActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper

    private lateinit var email: String
    private lateinit var amount: String
    private lateinit var card: Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Deposit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        App.getInstance().getAppComponent().inject(this)

        edtExpiration.addTextChangedListener(ExpiryWatcher())

        btnPay.setOnClickListener {
            card = Card.Builder(
                    edtCardNumber.text?.trim().toString(),
                    edtExpiration.text.toString().substring(0,2).toInt(),
                    edtExpiration.text.toString().substring(3,5).toInt(),
                    edtCVV.text.toString()
            ).build()
            if (card.isValid) {
                if (!edtAmount.text.toString().isEmpty()) {
                    initializeTransaction()
                } else {
                    showToast("Insert the amount you want to deposit")
                }
            } else {
                showToast("The card you entered is invalid, please confirm card details")
            }
        }
    }

    private fun initializeTransaction() {
        showProgressDialog()
        email = sharedPrefHelper.getUser().email
        amount = edtAmount.text?.trim().toString()
        //API
        apiService.initializeTransaction("test@gmail.com","5000")
                .enqueue(object : Callback<InitializeTransactionResp>{
                    override fun onFailure(call: Call<InitializeTransactionResp>?, t: Throwable?) {
                        showToast("Payment Initialization failed: " + t?.localizedMessage)
                        hideProgressDialog()
                    }

                    override fun onResponse(call: Call<InitializeTransactionResp>?, response: Response<InitializeTransactionResp>?) {
                        response?.body()?.let {
                            val accessCode = it.data.access_code
                            val reference = it.data.reference
                            hideProgressDialog()
                            performCharge(accessCode, reference)
                        }
                    }

                })
    }

    private fun performCharge(accessCode: String, reference: String) {
        showProgressDialog()
        val charge = Charge()
        charge.accessCode = accessCode
        charge.card = this.card

        PaystackSdk.chargeCard(this, charge, object : Paystack.TransactionCallback {
            override fun onSuccess(transaction: Transaction?) {
                Log.d("TEST", transaction?.reference)
                val ref = transaction?.reference
                hideProgressDialog()
                if (ref == null) {
                    confirmTransaction(ref)
                } else {
                    confirmTransaction(reference)
                }
            }

            override fun beforeValidate(transaction: Transaction?) {
                // Waiting
                hideProgressDialog()
            }

            override fun onError(error: Throwable?, transaction: Transaction?) {
                showToast("Payment Failed: " + error?.localizedMessage)
                hideProgressDialog()
            }
        })
    }

    private fun confirmTransaction(ref: String?) {
        showProgressDialog()
        val saveAuth = swtSaveCard.isChecked
        apiService.confirmTransaction(ref!!,saveAuth, "1234")
                .enqueue(object : Callback<ConfirmTransactionResp>{
                    override fun onFailure(call: Call<ConfirmTransactionResp>?, t: Throwable?) {
                        showToast("Payment Confirmation failed: " + t?.localizedMessage)
                        hideProgressDialog()
                    }

                    override fun onResponse(call: Call<ConfirmTransactionResp>?, response: Response<ConfirmTransactionResp>?) {
                        response?.body()?.let {
                            val transactionResp = it.data
                            //Send to Firebase
                            showToast("Payment Successful")
                            hideProgressDialog()
                            finish()
                        }
                    }
                })

        hideProgressDialog()
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

    private inner class ExpiryWatcher : TextWatcher {

        private val calendar: Calendar
        private val simpleDateFormat: SimpleDateFormat
        private var lastInput = ""

        init {
            calendar = Calendar.getInstance()
            simpleDateFormat = SimpleDateFormat("MM/yy")
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }

        override fun afterTextChanged(editable: Editable) {
            val input = editable.toString()

            try {
                calendar.time = simpleDateFormat.parse(input)
            } catch (e: ParseException) {
                if (editable.length == 2 && !lastInput.endsWith("/")) {
                    val month = Integer.parseInt(input)
                    if (month <= 12) {
                        edtExpiration.setText(edtExpiration.text.toString() + "/")
                        edtExpiration.setSelection(edtExpiration.text.toString().length)
                    } else {
                        edtExpiration.setText("12")
                        edtExpiration.setSelection(edtExpiration.text.toString().length)
                    }
                } else if (editable.length == 2 && lastInput.endsWith("/")) {
                    val month = Integer.parseInt(input)
                    if (month <= 12) {
                        edtExpiration.setText(edtExpiration.text.toString().substring(0, 1))
                        edtExpiration.setSelection(edtExpiration.text.toString().length)
                    } else {
                        edtExpiration.setText("12")
                        edtExpiration.setSelection(edtExpiration.text.toString().length)
                    }
                } else if (editable.length == 1) {
                    val month = Integer.parseInt(input)
                    if (month > 1) {
                        edtExpiration.setText("0" + edtExpiration.text.toString() + "/")
                        edtExpiration.setSelection(edtExpiration.text.toString().length)
                    }
                }

                lastInput = edtExpiration.text.toString()
            }

        }
    }
}
