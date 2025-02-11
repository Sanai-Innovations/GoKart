package com.sanai.gokart.domain.usecase

import com.sanai.gokart.data.models.GetProductResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.GoKartRepository

class GetProductsUseCase(private val goKartRepository: GoKartRepository) {
    suspend fun execute(country: String, page: Int): Resource<GetProductResponse> {
        return goKartRepository.getProducts(country, page)
    }
}