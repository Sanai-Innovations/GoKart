package com.sanai.gokart.data.api.services

import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardService {

    @GET("/v1/dashboard")
    suspend fun getDashboardData(): Response<DashboardResponse>
}