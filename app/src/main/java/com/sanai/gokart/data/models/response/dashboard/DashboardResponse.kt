package com.sanai.gokart.data.models.response.dashboard

import com.google.gson.annotations.SerializedName
import com.sanai.gokart.data.models.Product

data class DashboardResponse(
    @SerializedName("banners")
    var banners: List<Banner>?,

    @SerializedName("deals")
    var deals: List<Product>?,

    @SerializedName("bestSelling")
    var bestSelling: List<Product>?,

    @SerializedName("newCollection")
    val newCollection: List<Product>?
)