package com.sanai.gokart.data.models.response.dashboard

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("discount")
    var discount: String?,
    @SerializedName("subtitle")
    var subtitle: String?,
    @SerializedName("categoryId")
    var categoryId: Int?
)
