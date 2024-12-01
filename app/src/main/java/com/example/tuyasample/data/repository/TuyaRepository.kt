package com.example.tuyasample.data.repository

import com.example.tuyasample.data.common.ResultData
import com.example.tuyasample.data.common.getResultThirdParty
import com.example.tuyasample.data.remote.TuyaDataSource
import com.example.tuyasample.domain.model.IOTDeviceModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TuyaRepository @Inject constructor(
    private val tuyaDataSource: TuyaDataSource
) {
    suspend fun login(): ResultData<Boolean> = getResultThirdParty { tuyaDataSource.login() }

    suspend fun toggleSwitch(devId: String, param: String): ResultData<Boolean> = getResultThirdParty { tuyaDataSource.publishDps(devId, param) }

    fun getDeviceList(): Flow<ResultData<List<IOTDeviceModel>>> =
        tuyaDataSource.getDeviceList()
}