package com.sanai.gokart.presentation.util

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(private val pref: SharedPreferences) {

    companion object {
        private const val KEY_USER_ID = "key_user_id"
        private const val KEY_IS_LOGIN = "key_is_login"
    }

    fun getUserId(): Int {
        return pref.getInt(KEY_USER_ID, 0)
    }

    fun setUserId(userId: Int) {
        return pref.edit().apply { putInt(KEY_USER_ID, userId) }.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGIN, false)
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        return pref.edit().apply { putBoolean(KEY_IS_LOGIN, isLoggedIn) }.apply()
    }
}