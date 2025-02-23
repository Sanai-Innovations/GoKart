package com.sanai.gokart.data.repository.datasource.datasourceImpl

import com.sanai.gokart.data.api.services.DashboardService
import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.repository.datasource.DashboardRemoteDataSource
import retrofit2.Response

class DashboardRemoteDataSourceImpl(private val dashboardService: DashboardService) :
    DashboardRemoteDataSource {

    override suspend fun getDashboardData(): Response<DashboardResponse> {
        return dashboardService.getDashboardData()
    }
}