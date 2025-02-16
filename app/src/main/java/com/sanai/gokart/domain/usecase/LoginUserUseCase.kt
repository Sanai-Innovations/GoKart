package com.sanai.gokart.domain.usecase

import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.response.login.LoginResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.LoginRepository
import com.sanai.gokart.presentation.util.logging.Logger
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun execute(loginRequest: LoginRequest): Resource<LoginResponse> {
        Logger.d("Login with Username: ${loginRequest.username} and Password: ${loginRequest.password}")
        return loginRepository.loginUser(loginRequest)
    }
}