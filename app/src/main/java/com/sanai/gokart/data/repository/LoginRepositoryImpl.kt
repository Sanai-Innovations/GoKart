package com.sanai.gokart.data.repository

import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.LoginResponse
import com.sanai.gokart.data.models.response.RegisterResponse
import com.sanai.gokart.data.repository.datasource.LoginRemoteDataSource
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.LoginRepository
import com.sanai.gokart.presentation.util.AppPreferences
import retrofit2.Response

class LoginRepositoryImpl(
    private val sharedPrefs: AppPreferences,
    private val loginRemoteDataSource: LoginRemoteDataSource
) :
    LoginRepository {

    override suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return responseToResource(loginRemoteDataSource.loginUser(loginRequest))
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {

            // save in shared prefs if user has logged in successfully
            sharedPrefs.setUserLoggedIn(true)

            // send the response back
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        // send the error back
        return Resource.Error(
            code = response.code().toString(),
            message = response.message(),
            data = response.body()
        )
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): Resource<RegisterResponse> {
        return responseToResource(loginRemoteDataSource.registerUser(registerRequest))
    }
}