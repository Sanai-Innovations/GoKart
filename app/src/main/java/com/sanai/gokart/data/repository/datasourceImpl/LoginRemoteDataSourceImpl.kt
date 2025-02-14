package com.sanai.gokart.data.repository.datasourceImpl

import com.sanai.gokart.data.api.services.LoginService
import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.LoginResponse
import com.sanai.gokart.data.models.response.RegisterResponse
import com.sanai.gokart.data.repository.datasource.LoginRemoteDataSource
import retrofit2.Response

class LoginRemoteDataSourceImpl(private val loginService: LoginService) : LoginRemoteDataSource {

    override suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return loginService.loginUser(loginRequest)
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return loginService.registerUser(registerRequest)
    }
}