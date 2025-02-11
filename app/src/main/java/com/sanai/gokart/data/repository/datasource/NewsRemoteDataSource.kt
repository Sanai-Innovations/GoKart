package com.sanai.gokart.data.repository.datasource

import com.sanai.gokart.data.models.GetProductResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNewsHeadline(country: String, page: Int): Response<GetProductResponse>
}