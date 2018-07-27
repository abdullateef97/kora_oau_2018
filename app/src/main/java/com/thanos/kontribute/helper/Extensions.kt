package com.thanos.kontribute.helper

import android.app.Activity
import android.content.Context
import android.view.Window
import android.widget.Toast
import com.thanos.kontribute.ui.custom.ProgressDialog
import java.text.DecimalFormat

fun Activity.addFullScreenParameters() {
    this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Int.isValidPassword()
        = "$this".length > 3

var progressDialog: ProgressDialog? = null

fun Context.showProgressDialog() {
    progressDialog = ProgressDialog(this)
    progressDialog?.show()
}

fun Context.hideProgressDialog() {
    progressDialog?.cancel()
}

fun Int.toNaira(): String {
    val decimalFormat = DecimalFormat("#,###")
    val amount = decimalFormat.format(this)
    return String.format("₦%1\$s.00", amount)
}