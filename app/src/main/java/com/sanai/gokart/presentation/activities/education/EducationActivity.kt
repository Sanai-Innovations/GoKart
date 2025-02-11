package com.sanai.gokart.presentation.activities.education

import android.os.Bundle
import com.sanai.gokart.databinding.ActivityEducationBinding
import com.sanai.gokart.databinding.ActivitySplashBinding
import com.sanai.gokart.presentation.activities.base.BaseActivity

class EducationActivity : BaseActivity() {

    private lateinit var binding: ActivityEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeVariables()
    }

    private fun initializeVariables() {

    }
}