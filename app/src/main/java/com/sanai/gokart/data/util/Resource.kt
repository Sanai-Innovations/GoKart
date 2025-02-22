package com.sanai.gokart.data.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(code: String? = null, message: String? = null, data: T? = null) :
        Resource<T>(data, message)
}