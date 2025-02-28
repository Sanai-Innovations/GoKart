package com.sanai.gokart.presentation.viewmodel.product_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.usecase.product.AddToCartUseCase
import com.sanai.gokart.domain.usecase.product.AddToWishlistUseCase
import com.sanai.gokart.domain.usecase.product.GetProductDetailUseCase
import com.sanai.gokart.domain.usecase.product.RemoveFromWishlistUseCase
import com.sanai.gokart.presentation.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val addToWishListUseCase: AddToWishlistUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val removeFromWishlistUseCase: RemoveFromWishlistUseCase
) : ViewModel() {
    private val _productDetailResponse = MutableLiveData<Resource<ProductDetailResponse>>()
    val productDetailResponse: LiveData<Resource<ProductDetailResponse>> = _productDetailResponse

    private val _addToWishListResponse = MutableLiveData<Resource<Boolean>>()
    val addToWishListResponse: LiveData<Resource<Boolean>> = _addToWishListResponse

    private val _removeToWishListResponse = MutableLiveData<Resource<Boolean>>()
    val removeToWishListResponse: LiveData<Resource<Boolean>> = _removeToWishListResponse

    private val _addToCartResponse = MutableLiveData<Resource<Boolean>>()
    val addToCartResponse: LiveData<Resource<Boolean>> = _addToCartResponse

    fun getProductDetails(productId: Int) {
        Logger.d("ProductDetailViewModel: Get product details data")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _productDetailResponse.postValue(Resource.Loading())
                val result = getProductDetailUseCase.execute(productId)
                val productDetailResponse = result.data
                if (productDetailResponse != null) {
                    _productDetailResponse.postValue(Resource.Success(productDetailResponse))
                } else {
                    _productDetailResponse.postValue(Resource.Error("No data found"))
                }
                Logger.d("ProductDetailViewModel: ProductDetail Response: $productDetailResponse")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }

    fun addToWishlist(productId: Int) {
        Logger.d("ProductDetailViewModel: Add To Wishlist")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _addToWishListResponse.postValue(Resource.Loading())
                val result = addToWishListUseCase.execute(productId)
                val addToWishListResponse = result.data
                if (addToWishListResponse != null) {
                    _addToWishListResponse.postValue(Resource.Success(addToWishListResponse))
                } else {
                    _addToWishListResponse.postValue(Resource.Error("No data found"))
                }
                Logger.d("ProductDetailViewModel: Add To Wishlist Response: $addToWishListUseCase")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }

    fun removeFromWishList(productId: Int) {
        Logger.d("ProductDetailViewModel: Remove From Wishlist")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _removeToWishListResponse.postValue(Resource.Loading())
                val result = removeFromWishlistUseCase.execute(productId)
                val removeToWishListResponse = result.data
                if (removeToWishListResponse != null) {
                    _addToWishListResponse.postValue(Resource.Success(removeToWishListResponse))
                } else {
                    _addToWishListResponse.postValue(Resource.Error("No data found"))
                }
                Logger.d("ProductDetailViewModel: Remove From Wishlist Response: $removeToWishListResponse")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }

    fun addToCart(productId: Int) {
        Logger.d("ProductDetailViewModel: Add to cart")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _addToCartResponse.postValue(Resource.Loading())
                val result = addToCartUseCase.execute(productId)
                val addToCartResponse = result.data
                if (addToCartResponse != null) {
                    _addToCartResponse.postValue(Resource.Success(addToCartResponse))
                } else {
                    _addToCartResponse.postValue(Resource.Error("No data found"))
                }
                Logger.d("ProductDetailViewModel: Add to cart Response: $addToCartResponse")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }
}