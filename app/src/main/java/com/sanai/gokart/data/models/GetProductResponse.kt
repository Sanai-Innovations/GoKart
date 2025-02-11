package com.sanai.gokart.data.models


import com.google.gson.annotations.SerializedName

data class GetProductResponse(
    @SerializedName("status")
    var status: String?,
    @SerializedName("products")
    var products: List<Product>?
)