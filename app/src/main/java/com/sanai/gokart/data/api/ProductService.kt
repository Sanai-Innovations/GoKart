package com.sanai.gokart.data.api

import com.sanai.gokart.data.models.GetProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("/v1/products")
    suspend fun getAllProducts(): Response<GetProductResponse>
}