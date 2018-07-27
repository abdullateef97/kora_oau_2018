package com.thanos.kontribute.helper

import android.content.SharedPreferences

class SharedPrefHelper(var sharedPref: SharedPreferences) {

    private val IS_LOGGED_IN = "isLoggedIn"

    fun isLoggedIn(): Boolean = sharedPref.getBoolean(IS_LOGGED_IN, false)

    fun setLoggedInCompleted() {
        sharedPref.edit()
                .putBoolean(IS_LOGGED_IN, true)
                .apply()
    }

}