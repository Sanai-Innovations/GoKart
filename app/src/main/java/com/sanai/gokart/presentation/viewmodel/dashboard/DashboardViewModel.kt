package com.sanai.gokart.presentation.viewmodel.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanai.gokart.data.models.response.dashboard.DashboardResponse
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.domain.usecase.DashboardUseCase
import com.sanai.gokart.presentation.util.logging.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val dashboardUseCase: DashboardUseCase) :
    ViewModel() {
    private val _dashboardResponse = MutableLiveData<Resource<DashboardResponse>>()
    val dashboardResponse: LiveData<Resource<DashboardResponse>> = _dashboardResponse

    fun getDashboardData() {
        Logger.d("DashboardViewModel: Get dashboard data")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _dashboardResponse.postValue(Resource.Loading())
                val result = dashboardUseCase.execute()
                val dashboardResponse = result.data
                if (dashboardResponse != null) {
                    _dashboardResponse.postValue(Resource.Success(dashboardResponse))
                } else {
                    _dashboardResponse.postValue(Resource.Error("No data found"))
                }
                Logger.d("DashboardViewModel: Dashboard Response: $dashboardResponse")
            }
        } catch (e: Exception) {
            Logger.e(e.message.toString())
        }
    }
}