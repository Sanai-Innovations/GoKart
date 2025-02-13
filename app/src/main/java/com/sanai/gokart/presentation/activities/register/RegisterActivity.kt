package com.sanai.gokart.presentation.activities.register

import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.sanai.gokart.R
import com.sanai.gokart.databinding.ActivityRegisterBinding
import com.sanai.gokart.presentation.activities.base.BaseActivity

class RegisterActivity : BaseActivity() {

    private var isMaleSelected = false
    private var isFemaleSelected = false

    private lateinit var toolbar: Toolbar
    private lateinit var loadingBar: ProgressBar
    private lateinit var loginTextView: TextView
    private lateinit var userNameEditText: EditText
    private lateinit var maleGenderImage: ImageView
    private lateinit var lastNameEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var femaleGenderImage: ImageView
    private lateinit var phoneNumberEditText: EditText
    private lateinit var maleGenderTickImage: ImageView
    private lateinit var passwordNameEditText: EditText
    private lateinit var registerButton: MaterialButton
    private lateinit var femaleGenderTickImage: ImageView
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVariables()
    }

    private fun initializeVariables() {
        toolbar = binding.toolbarRegisterActivity
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // initialize components
        loginTextView = binding.tvLogin
        loadingBar = binding.progressBar
        userNameEditText = binding.email
        maleGenderImage = binding.ivMale
        lastNameEditText = binding.lastName
        femaleGenderImage = binding.ivFemale
        registerButton = binding.btnRegister
        firstNameEditText = binding.firstName
        passwordNameEditText = binding.password
        passwordNameEditText = binding.password
        maleGenderTickImage = binding.ivMaleTick
        phoneNumberEditText = binding.phoneNumber
        femaleGenderTickImage = binding.ivFemaleTick

        // set click listeners
        registerButton.setOnClickListener {

            if (isFirstNameValid(firstNameEditText.text.toString())
                && isSecondNameValid(lastNameEditText.text.toString())
                && isUserNameValid(userNameEditText.text.toString())
                && isPasswordValid(passwordNameEditText.text.toString())
                && isPhoneNumberValid(phoneNumberEditText.text.toString())
                && (isFemaleSelected || isMaleSelected)
            ) {
                loadingBar.visibility = VISIBLE
                // TODO Hit API to create new user
            } else {
                showError(getString(R.string.lbl_error_register))
            }
        }
        femaleGenderImage.setOnClickListener {
            isMaleSelected = false
            isFemaleSelected = true
            femaleGenderTickImage.visibility = VISIBLE
            maleGenderTickImage.visibility = GONE
        }
        maleGenderImage.setOnClickListener {
            isMaleSelected = true
            isFemaleSelected = false
            femaleGenderTickImage.visibility = GONE
            maleGenderTickImage.visibility = VISIBLE
        }
        loginTextView.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    // A placeholder password validation check
    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.length == 10
    }

    // A placeholder password validation check
    private fun isFirstNameValid(firstname: String): Boolean {
        return firstname.isNotEmpty()
    }

    // A placeholder password validation check
    private fun isSecondNameValid(secondName: String): Boolean {
        return secondName.isNotEmpty()
    }
}