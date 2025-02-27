package com.sanai.gokart.domain.usecase.product

import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(productId: Int): Resource<ProductDetailResponse> {
        return productRepository.getProductDetail(productId)
    }
}