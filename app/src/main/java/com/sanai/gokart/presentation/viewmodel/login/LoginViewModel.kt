package com.sanai.gokart.presentation.viewmodel.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanai.gokart.R
import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.domain.usecase.LoginUserUseCase
import com.sanai.gokart.presentation.activities.login.LoginResult
import com.sanai.gokart.presentation.util.logging.Logger
import com.vans.gokart.ui.login.LoginFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
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

    fun login(username: String, password: String) {
        Logger.d("LoginViewModel: Login with Username: $username and Password: $password")
        val loginRequest = LoginRequest(username = username, password = password)
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val result = loginUserUseCase.execute(loginRequest)
                val userProfile = result.data?.userInfo
                Logger.d("LoginViewModel: User profile: $userProfile")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }
}