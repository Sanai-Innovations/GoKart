package com.sanai.gokart.presentation.activities.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textview.MaterialTextView
import com.sanai.gokart.R
import com.sanai.gokart.databinding.ActivityLoginBinding
import com.sanai.gokart.presentation.activities.base.BaseActivity
import com.sanai.gokart.presentation.activities.home.HomeActivity
import com.sanai.gokart.presentation.activities.register.RegisterActivity
import com.sanai.gokart.presentation.viewmodel.login.LoginViewModel
import com.sanai.gokart.presentation.viewmodel.login.LoginViewModelFactory
import com.vans.gokart.ui.login.LoggedInUserView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private lateinit var loginButton: Button
    private lateinit var loadingBar: ProgressBar
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginAsGuestButton: Button
    private lateinit var userNameEditText: EditText
    private lateinit var binding: ActivityLoginBinding
    private lateinit var passwordNameEditText: EditText
    private lateinit var registerTextView: MaterialTextView

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get the binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeVariables()
        setClickListeners()
        setEditTextChangeListener()

        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            loginButton.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                userNameEditText.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                passwordNameEditText.error = getString(loginState.passwordError)
            }
        })

        viewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loadingBar.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })
    }

    private fun initializeVariables() {
        loginButton = binding.login
        loadingBar = binding.loading
        userNameEditText = binding.username
        registerTextView = binding.tvRegister
        passwordNameEditText = binding.password
        loginAsGuestButton = binding.loginAsGust

        // view model
        viewModel = ViewModelProvider.create(this, loginViewModelFactory)[LoginViewModel::class]
    }

    private fun setClickListeners() {
        loginButton.setOnClickListener {
            loadingBar.visibility = View.VISIBLE
            viewModel.login(
                userNameEditText.text.toString(), passwordNameEditText.text.toString()
            )
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }

        registerTextView.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun setEditTextChangeListener() {

        // username text changes
        userNameEditText.afterTextChanged {
            viewModel.loginDataChanged(
                userNameEditText.text.toString(), passwordNameEditText.text.toString()
            )
        }

        // password text changes
        passwordNameEditText.apply {
            afterTextChanged {
                viewModel.loginDataChanged(
                    userNameEditText.text.toString(), passwordNameEditText.text.toString()
                )
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext, "$welcome $displayName", Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}