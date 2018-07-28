package com.thanos.kontribute.helper

import android.content.SharedPreferences
import com.thanos.kontribute.data.model.User

class SharedPrefHelper(var sharedPref: SharedPreferences) {

    private val IS_LOGGED_IN = "isLoggedIn"

    fun isLoggedIn(): Boolean = sharedPref.getBoolean(IS_LOGGED_IN, false)

    fun setLoggedInCompleted() {
        sharedPref.edit()
                .putBoolean(IS_LOGGED_IN, true)
                .apply()
    }

    fun getUser(): User {
        return User(
                sharedPref.getString(PREF_ID, ""),
                sharedPref.getString(PREF_NAME,""),
                sharedPref.getString(PREF_EMAIL, ""),
                sharedPref.getString(PREF_PICTURE, ""),
                sharedPref.getString(PREF_BANK_ACCOUNT, "")
        )
    }

}