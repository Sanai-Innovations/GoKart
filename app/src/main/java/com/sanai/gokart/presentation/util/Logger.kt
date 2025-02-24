package com.sanai.gokart.presentation.util

import android.util.Log
import com.sanai.gokart.BuildConfig

object Logger {
    fun d(value: String) {
        log(Log.DEBUG, Constants.APP_TAG, value, null)
    }

    fun e(value: String) {
        log(Log.ERROR, Constants.APP_TAG, value, null)
    }

    fun e(value: String, e: Throwable?) {
        log(Log.ERROR, Constants.APP_TAG, value, e)
    }

    fun i(value: String) {
        log(Log.INFO, Constants.APP_TAG, value, null)
    }

    fun i(value: String, e: Throwable?) {
        log(Log.INFO, Constants.APP_TAG, value, e)
    }

    fun v(tag: String, value: String) {
        log(Log.VERBOSE, tag, value, null)
    }

    fun w(tag: String, value: String) {
        log(Log.WARN, tag, value, null)
    }

    private fun log(level: Int, tag: String, value: String, e: Throwable?) {
        if (!BuildConfig.DEBUG) {
            return
        }

        when (level) {
            Log.DEBUG -> Log.d(tag, value, e)
            Log.ERROR -> Log.e(tag, value, e)
            Log.INFO -> Log.i(tag, value, e)
            Log.VERBOSE -> Log.v(tag, value, e)
            Log.WARN -> Log.w(tag, value, e)
        }
    }
}
