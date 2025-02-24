package com.sanai.gokart.data.api.interceptor

import android.app.Application
import android.text.TextUtils
import com.sanai.gokart.presentation.util.Constants.EMPTY_STRING
import com.sanai.gokart.presentation.util.Logger
import java.io.IOException
import java.io.UnsupportedEncodingException
import javax.inject.Inject

class MockResponseProvider @Inject constructor(private val app: Application) {

    private val folderName = "response/"
    private val jsonExtension = ".json"
    private var filePath: String? = null

    fun setPath(path: String, param: String?) {
        filePath = path.replace("/".toRegex(), "_")
        if (param != null) {
            filePath = when (param) {
                "TRN" -> filePath + "_trn"
                else -> filePath + EMPTY_STRING
            }
        }
    }

    fun getJsonString(): String {
        if (!TextUtils.isEmpty(filePath)) {
            var json: String
            val filePathAbsolute = folderName + filePath + jsonExtension
            try {
                app.baseContext.assets.open(filePathAbsolute).use { `is` ->
                    val size = `is`.available()
                    val buffer = ByteArray(size)
                    `is`.read(buffer)
                    json = String(buffer, charset("UTF-8"))
                }
            } catch (e: UnsupportedEncodingException) {
                Logger.e("Exception: MockResponseProvider", e)
                return EMPTY_STRING
            } catch (ex: IOException) {
                Logger.e("Exception: MockResponseProvider", ex)
                return EMPTY_STRING
            }
            return json
        }
        return EMPTY_STRING
    }
}
