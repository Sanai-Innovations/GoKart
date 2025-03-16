package com.sanai.gokart.presentation.fragments.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanai.gokart.data.models.response.cart.CartProductItem
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.usecase.product.GetCartItemsUseCase
import com.sanai.gokart.presentation.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val getCartItemsUseCase: GetCartItemsUseCase) :
    ViewModel() {

    private val _cartItems = MutableLiveData<Resource<List<CartProductItem>>>()
    val cartItems: LiveData<Resource<List<CartProductItem>>> = _cartItems

    fun getCartItems() {
        Logger.d("CartViewModel: Get cart items")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _cartItems.postValue(Resource.Loading())
                val result = getCartItemsUseCase.execute()
                result.collect {
                    _cartItems.postValue(it)
                }
                Logger.d("CartViewModel: Cart items: $cartItems")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }
}