package com.sanai.gokart.presentation.activities.product_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.databinding.ActivityProductDetailBinding
import com.sanai.gokart.presentation.util.Logger
import com.sanai.gokart.presentation.viewmodel.product_detail.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var binding: ActivityProductDetailBinding

    private var productId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeVariables()
    }

    private fun initializeVariables() {
        // set action bar
        setSupportActionBar(binding.toolbar)

        // get view model
        viewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]

        getIntentData()
        viewModel.getProductDetails(productId)
        bindObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnAddToWishList.setOnClickListener {
            viewModel.addToWishlist(productId)
        }
        binding.btnAddToCart.setOnClickListener {
            viewModel.addToCart(productId)
        }
    }

    private fun getIntentData() {
        productId = intent.getIntExtra("productId", 0)
    }

    private fun bindObservers() {
        viewModel.productDetailResponse.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Logger.e("DashboardFragment: $it.message.toString()")
                }

                is Resource.Loading -> {
                    Logger.e("DashboardFragment: Loading")
                }

                is Resource.Success -> {
                    Logger.e("DashboardFragment: Success ${it.data}")
                }
            }
        }
    }
}