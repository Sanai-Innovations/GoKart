package com.sanai.gokart.data.models.response.register

import com.google.gson.annotations.SerializedName
import com.sanai.gokart.data.models.response.user.UserProfile

data class RegisterResponse(
    @SerializedName("code")
    var statusCode: String?,

    @SerializedName("message")
    var statusMessage: String?,

    @SerializedName("userProfile")
    val userInfo: UserProfile?
)