package com.sanai.gokart.data.repository.repositoryImpl

import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.repository.BaseApiResponse
import com.sanai.gokart.data.repository.datasource.DashboardRemoteDataSource
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.DashboardRepository

class DashboardRepositoryImpl(private val dashboardRemoteDataSource: DashboardRemoteDataSource) :
    BaseApiResponse(), DashboardRepository {

    override suspend fun getDashboardData(): Resource<DashboardResponse> {
        return safeApiCall { dashboardRemoteDataSource.getDashboardData() }
    }
}