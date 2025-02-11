package com.sanai.gokart.data.repository.datasourceImpl

import com.sanai.gokart.data.api.ProductService
import com.sanai.gokart.data.models.GetProductResponse
import com.sanai.gokart.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class GoKartRemoteDataSourceImpl(private val productService: ProductService) :
    NewsRemoteDataSource {

    override suspend fun getNewsHeadline(
        country: String,
        page: Int
    ): Response<GetProductResponse> {
        return productService.getAllProducts()
    }
}