package com.sanai.gokart.domain.repository

import com.sanai.gokart.data.models.GetProductResponse
import com.sanai.gokart.data.util.Resource

interface GoKartRepository {

    suspend fun getProducts(country: String, page: Int): Resource<GetProductResponse>
}
