package com.sanai.gokart.data.repository.datasource

import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.LoginResponse
import com.sanai.gokart.data.models.response.RegisterResponse
import retrofit2.Response

interface LoginRemoteDataSource {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>
    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>
}