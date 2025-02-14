package com.sanai.gokart.presentation.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanai.gokart.presentation.viewmodel.NewsViewModel

class NewsViewModelFactory(
    private val app: Application,
//    private val getNewsHeadlinesUseCase: GetProductsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            //getNewsHeadlinesUseCase
        ) as T
    }
}