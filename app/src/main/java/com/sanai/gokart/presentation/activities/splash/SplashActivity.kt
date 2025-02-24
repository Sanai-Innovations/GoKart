package com.sanai.gokart.presentation.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sanai.gokart.databinding.ActivitySplashBinding
import com.sanai.gokart.presentation.activities.base.BaseActivity
import com.sanai.gokart.presentation.activities.education.EducationActivity
import com.sanai.gokart.presentation.activities.dashboard.HomeActivity
import com.sanai.gokart.presentation.util.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    @Inject
    lateinit var preferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the binding
        setContentView(ActivitySplashBinding.inflate(layoutInflater).root)
        initializeVariables()
    }

    private fun initializeVariables() {
        moveToNext()
    }

    private fun moveToNext() {

        // getting the main looper
        Handler(Looper.getMainLooper()).postDelayed({

            if (preferences.isUserLoggedIn()) {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, EducationActivity::class.java))
            }
            finish()
        }, 2000)
    }
}