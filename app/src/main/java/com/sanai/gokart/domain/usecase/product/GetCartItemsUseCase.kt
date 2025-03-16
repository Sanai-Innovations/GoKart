package com.sanai.gokart.domain.usecase.product

import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(): Flow<Resource<List<CartProductItem>>> {
        return productRepository.getProductsFromCart()
    }
}