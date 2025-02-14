package com.sanai.gokart.data.models.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var statusCode: String?,
    @SerializedName("message")
    var statusMessage: String?
)