package com.sanai.gokart.data.models.response.user


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    @SerializedName("userId")
    var userId: Long?,

    @SerializedName("userName")
    var userName: String?,

    @SerializedName("salutation")
    var salutation: String?,

    @SerializedName("firstName")
    var firstName: String?,

    @SerializedName("lastName")
    var lastName: String?,

    @SerializedName("profilePicUrl")
    var profilePicUrl: String?,

    @SerializedName("email")
    var email: String?,

    @SerializedName("mobile")
    var mobile: String?,

    @SerializedName("createdTimeStamp")
    var createdTimeStamp: String?,

    @SerializedName("userProfileTimeStamp")
    var userProfileTimeStamp: String?,

    @SerializedName("lastLoggedTimeStamp")
    val lastLoggedTimeStamp: String?
) : Parcelable