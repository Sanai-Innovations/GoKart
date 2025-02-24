package com.sanai.gokart.domain.usecase

import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.DashboardRepository
import com.sanai.gokart.presentation.util.Logger
import javax.inject.Inject

class DashboardUseCase @Inject constructor(private val dashboardRepository: DashboardRepository) {
    suspend fun execute(): Resource<DashboardResponse> {
        Logger.d("Request for Dashboard")
        return dashboardRepository.getDashboardData()
    }
}