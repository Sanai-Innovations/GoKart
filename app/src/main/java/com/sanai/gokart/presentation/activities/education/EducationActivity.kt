package com.sanai.gokart.presentation.activities.education

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.sanai.gokart.R
import com.sanai.gokart.databinding.ActivityEducationBinding
import com.sanai.gokart.presentation.activities.base.BaseActivity
import com.sanai.gokart.presentation.activities.login.LoginActivity
import com.sanai.gokart.presentation.activities.register.RegisterActivity
import com.sanai.gokart.presentation.adapter.EducationViewPagerAdapter

class EducationActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEducationBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeVariables()
    }

    private fun initializeVariables() {
        viewPager = binding.pager

        setupViewPager()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.loginButton.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)
    }

    private fun setupViewPager() {
        val adapter = EducationViewPagerAdapter()
        viewPager.adapter = adapter

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.loginButton -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            R.id.registerButton -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
}