package com.sanai.gokart.data.models.response.product_detail

import com.google.gson.annotations.SerializedName

data class ProductColour(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("code")
    var code: String? = null
)