package com.sanai.gokart.presentation.util

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(private val pref: SharedPreferences) {

    companion object {
        private const val KEY_IS_LOGIN = "key_is_login"
    }

    fun isUserLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGIN, false)
    }

    fun setUserLoggedIn(isLoggedIn: Boolean)  {
        return pref.edit().apply{putBoolean(KEY_IS_LOGIN, isLoggedIn)}.apply()
    }
}