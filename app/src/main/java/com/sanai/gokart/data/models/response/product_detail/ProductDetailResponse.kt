package com.sanai.gokart.data.models.response.product_detail

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("shortDesc")
    var shortDesc: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("productSize")
    var productSize: List<ProductSize>? = null,
    @SerializedName("productColours")
    var productColours: List<ProductColour>? = null,
    @SerializedName("categoryId")
    var categoryId: Int = 1,
    @SerializedName("quantity")
    var quantity: Int = 0,
    @SerializedName("price")
    var price: Double = 0.0,
    @SerializedName("isWishList")
    var isWishList: Boolean = false,
    @SerializedName("discount")
    var discount: Double = 0.0,
    @SerializedName("imageUrl")
    var imageUrl: String? = null
)