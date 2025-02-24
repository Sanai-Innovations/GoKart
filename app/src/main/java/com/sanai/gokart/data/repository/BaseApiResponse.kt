package com.sanai.gokart.data.repository

import com.sanai.gokart.data.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(it)
                }
            }

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