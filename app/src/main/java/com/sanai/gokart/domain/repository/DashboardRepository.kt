package com.sanai.gokart.domain.repository

import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.util.Resource

interface DashboardRepository {

    suspend fun getDashboardData(): Resource<DashboardResponse>
}
