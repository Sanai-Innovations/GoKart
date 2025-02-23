package com.sanai.gokart.data.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    var productId: Int?,

    @SerializedName("productImages")
    var productImages: List<String>?,

    @SerializedName("discountPercent")
    var discount: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("subtitle")
    val subtitle: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("categoryId")
    val categoryId: Int?,

    @SerializedName("subCategoryId")
    val subCategoryId: Int?
)