package com.sanai.gokart.data.repository.repositoryImpl

import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.repository.datasource.DashboardRemoteDataSource
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.DashboardRepository
import com.sanai.gokart.presentation.util.logging.Logger
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DashboardRepositoryImpl(private val dashboardRemoteDataSource: DashboardRemoteDataSource) :
    DashboardRepository {

    override suspend fun getDashboardData(): Resource<DashboardResponse> {
        return responseToResource(dashboardRemoteDataSource.getDashboardData())
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        try {
            if (response.isSuccessful) {
                Logger.d("Login is successful")

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