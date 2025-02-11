package com.sanai.gokart.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "product_table")
@Parcelize
data class Product(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var productId: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("shortDesc")
    var shortDesc: String? = null,
    @SerializedName("categoryId")
    var categoryId: Int? = null,
    @SerializedName("subCategoryId")
    var subCategoryId: Int? = null,
    @SerializedName("quantity")
    var quantity: Int = 0,
    @SerializedName("price")
    var price: Double = 0.0,
    @SerializedName("isFavourite")
    var isFavourite: Boolean = false,
    @SerializedName("discount")
    var discount: Double = 0.0,
    @SerializedName("createdDate")
    var createdDate: String? = null,
    @SerializedName("lastUpdated")
    var lastUpdated: String? = null,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = null
) : Parcelable