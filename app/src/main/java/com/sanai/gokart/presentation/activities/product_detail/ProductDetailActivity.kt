package com.sanai.gokart.presentation.activities.product_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sanai.gokart.data.models.response.product_detail.ProductDetailResponse
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
    private var productDetailResponse: ProductDetailResponse? = null

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
            viewModel.addToCart(productDetailResponse!!)
        }
    }

    private fun getIntentData() {
        productId = intent.getIntExtra("productId", 0)
    }

    private fun bindObservers() {
        viewModel.productDetailResponse.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Logger.e("ProductDetailActivity: $it.message.toString()")
                }

                is Resource.Loading -> {
                    Logger.e("ProductDetailActivity: Loading")
                }

                is Resource.Success -> {
                    Logger.e("ProductDetailActivity: Success ${it.data}")
                    productDetailResponse = it.data
                    updateUI()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI() {
        binding.tvDiscount.text = "${productDetailResponse?.discount.toString()}% off"
        binding.tvPrice.text = "Rs. ${productDetailResponse?.marketPrice.toString()}"
        binding.tvSavePrice.text = "Save Rs. ${productDetailResponse?.finalPrice.toString()}"
        binding.tvTitle.text = productDetailResponse?.title
        binding.tvDescription.text = productDetailResponse?.description
        binding.tvCategory.text = productDetailResponse?.categoryName
        Glide.with(binding.imgProduct.context)
            .load(productDetailResponse?.imageUrl)
            .into(binding.imgProduct)
    }
}