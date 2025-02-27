package com.sanai.gokart.data.models.response.product_detail

import com.google.gson.annotations.SerializedName

data class ProductSize(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("value")
    var value: String? = null
)