package com.example.tuyasample.presentation.iot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuyasample.domain.model.IOTDeviceModel
import com.example.tuyasample.domain.usecase.FetchDeviceUseCase
import com.example.tuyasample.domain.usecase.LoginTuyaUseCase
import com.example.tuyasample.domain.usecase.ToggleSwitchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IOTViewModel @Inject constructor(
    loginTuyaUseCase: LoginTuyaUseCase,
    fetchDeviceUseCase: FetchDeviceUseCase,
    private val toggleSwitchUseCase: ToggleSwitchUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    internal val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLogin = MutableStateFlow(false)
    internal val isLogin: StateFlow<Boolean> = _isLogin

    private val _deviceList = MutableStateFlow<List<IOTDeviceModel>>(emptyList())
    internal val deviceList: StateFlow<List<IOTDeviceModel>> = _deviceList

    init {
        viewModelScope.launch {
            loginTuyaUseCase
                .onSuccess {
                    _isLogin.value = it
                }
                .onError { code, message ->

                }
                .execute()

            fetchDeviceUseCase
                .onSuccess {
                    _isLoading.value = false
                    _deviceList.value = it
                }
                .onError { code, message ->
                    _isLoading.value = false
                    println("fetchDeviceUseCase $code $message")
                }
                .execute()
        }
    }

    fun toggleSwitch(param: ToggleSwitchUseCase.Param){
        viewModelScope.launch {
            toggleSwitchUseCase.addParams(param)
                .onSuccess {
                    _deviceList.value = _deviceList.value.map {
                        if (it.id == param.devId) {
                            it.copy(isOn = param.value) // Update the specific device
                        } else {
                            it
                        }
                    }
                }
                .onError { code, message ->

                }
                .execute()
        }
    }
}