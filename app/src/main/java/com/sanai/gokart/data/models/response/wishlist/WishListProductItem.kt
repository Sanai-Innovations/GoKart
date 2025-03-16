package com.sanai.gokart.data.models.response.wishlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "wish_list_table")
data class WishListProductItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

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
    val subtitle: String?,
)