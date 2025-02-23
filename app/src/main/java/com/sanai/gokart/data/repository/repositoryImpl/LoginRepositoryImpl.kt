package com.sanai.gokart.data.repository.repositoryImpl

import com.sanai.gokart.data.models.request.LoginRequest
import com.sanai.gokart.data.models.request.RegisterRequest
import com.sanai.gokart.data.models.response.login.LoginResponse
import com.sanai.gokart.data.models.response.register.RegisterResponse
import com.sanai.gokart.data.repository.datasource.LoginRemoteDataSource
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.LoginRepository
import com.sanai.gokart.presentation.util.prefs.AppPreferences
import com.sanai.gokart.presentation.util.logging.Logger
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LoginRepositoryImpl(
    private val sharedPrefs: AppPreferences,
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return responseToResource(loginRemoteDataSource.loginUser(loginRequest))
    }

    override suspend fun registerUser(registerRequest: RegisterRequest): Resource<RegisterResponse> {
        return responseToResource(loginRemoteDataSource.registerUser(registerRequest))
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {

        try {
            if (response.isSuccessful) {
                Logger.d("Login is successful")

                // save in shared prefs if user has logged in successfully
                sharedPrefs.setUserLoggedIn(true)

                // send the response back
                response.body()?.let { result ->
                    return Resource.Success(result)
                }
            }

            Logger.d("Login is failure ${response.message()}")
            // send the error back
            return Resource.Error(
                code = response.code().toString(),
                message = response.message(),
                data = response.body()
            )
        } catch (e: UnknownHostException) {
            return Resource.Error(message = "Network connectivity issue")
        } catch (e: ConnectException) {
            return Resource.Error(message = "Connection refused")
        } catch (e: SocketTimeoutException) {
            return Resource.Error(message = "Connection timed out")
        } catch (e: HttpException) {
            return Resource.Error(message = e.message)
        } catch (e: Exception) {
            return Resource.Error(message = e.message) // Generic error handling
        }
    }
}