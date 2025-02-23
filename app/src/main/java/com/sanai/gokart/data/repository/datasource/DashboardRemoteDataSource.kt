package com.sanai.gokart.data.repository.datasource

import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import retrofit2.Response

interface DashboardRemoteDataSource {
    suspend fun getDashboardData(): Response<DashboardResponse>
}