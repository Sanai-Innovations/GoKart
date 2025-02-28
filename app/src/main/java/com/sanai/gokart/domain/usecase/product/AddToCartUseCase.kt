package com.sanai.gokart.domain.usecase.product

import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.ProductRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(productId: Int): Resource<Boolean> {
        return productRepository.addProductToCart(productId)
    }
}