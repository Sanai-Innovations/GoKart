package com.sanai.gokart.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sanai.gokart.data.models.response.LoginResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.newsapiclient.presentation.util.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    //private val newsHeadlinesUseCase: GetProductsUseCase
) : AndroidViewModel(app) {
    val newsHeadlines: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            newsHeadlines.postValue(Resource.Loading())
            try {
                if (Utility.isInternetAvailable(app)) {
//                    val apiResponse = newsHeadlinesUseCase.execute(country, page)
//                    newsHeadlines.postValue(apiResponse)
                } else {
                    newsHeadlines.postValue(Resource.Error(message = "Internet is not available"))
                }
            } catch (exception: Exception) {
                newsHeadlines.postValue(Resource.Error(message = exception.message.toString()))
            }
        }
    }
}