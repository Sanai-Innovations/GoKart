package com.sanai.gokart.domain.repository

import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.login.LoginResponse
import com.sanai.gokart.data.models.response.register.RegisterResponse
import com.sanai.gokart.data.util.Resource

interface LoginRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse>
    suspend fun registerUser(registerRequest: RegisterRequest): Resource<RegisterResponse>
}
