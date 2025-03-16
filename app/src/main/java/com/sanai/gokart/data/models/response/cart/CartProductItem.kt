package com.sanai.gokart.data.models.response.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_table")
data class CartProductItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @SerializedName("title")
    var title: String?,

    @SerializedName("productId")
    var productId: Int?,

    @SerializedName("thumbnail")
    var thumbnail: String?,

    @SerializedName("quantity")
    var quantity: Int?,

    @SerializedName("marketPrice")
    val marketPrice: Double?,

    @SerializedName("finalPrice")
    val finalPrice: Double?,

    @SerializedName("discount")
    val discount: Double?,
)