/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */
package com.sanai.gokart.data.api.interceptor

import android.text.TextUtils
import com.sanai.gokart.BuildConfig
import com.sanai.gokart.presentation.util.Constants.EMPTY_STRING
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class MockAPIInterceptor @Inject constructor(private val mockResponseProvider: MockResponseProvider) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (BuildConfig.DEBUG) {
            try {
                // get the request URL
                var urlPath = URLDecoder.decode(
                    request.url.toUrl().path,
                    StandardCharsets.UTF_8.displayName()
                )

                // get the path parameters
                val param = EMPTY_STRING

                // replace the base url to empty string
                val response = Response.Builder()
                if (urlPath.startsWith(BuildConfig.BASE_URL)) {
                    urlPath = urlPath.replace(BuildConfig.BASE_URL, "")
                }

                // get the json response
                val jsonResponse = getJsonResponse(urlPath, param)
                val type = EMPTY_STRING.toMediaTypeOrNull()
                if (!TextUtils.isEmpty(jsonResponse)) {
                    val body = jsonResponse.toResponseBody(type)
                    response.body(body)
                    response.request(request)
                    response.protocol(Protocol.HTTP_1_0)
                    response.code(200)
                    response.message("Success")
                    return response.build()
                } else {
                    val body = jsonResponse.toResponseBody(type)
                    response.body(body)
                    response.request(request)
                    response.protocol(Protocol.HTTP_1_0)
                    response.code(500)
                    response.message("Feature not available")
                    return response.build()
                }
            } catch (ex: UnsupportedEncodingException) {
                throw RuntimeException(ex.cause)
            }
        }
        return chain.proceed(chain.request())
    }

    @Synchronized
    private fun getJsonResponse(urlPath: String, param: String): String {
        mockResponseProvider.setPath(urlPath, param)
        return mockResponseProvider.getJsonString()
    }
}
