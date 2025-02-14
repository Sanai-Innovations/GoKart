package com.sanai.gokart.data.api.services

import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.LoginResponse
import com.sanai.gokart.data.models.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/v1/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/v1/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}