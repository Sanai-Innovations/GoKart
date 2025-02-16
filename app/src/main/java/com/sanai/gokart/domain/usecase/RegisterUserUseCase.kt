package com.sanai.gokart.domain.usecase

import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.register.RegisterResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.LoginRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun execute(registerRequest: RegisterRequest): Resource<RegisterResponse> {
        return loginRepository.registerUser(registerRequest)
    }
}