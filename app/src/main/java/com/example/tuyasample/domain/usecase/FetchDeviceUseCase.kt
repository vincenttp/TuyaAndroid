package com.example.tuyasample.domain.usecase

import com.example.tuyasample.data.common.ResultData
import com.example.tuyasample.data.repository.TuyaRepository
import com.example.tuyasample.domain.model.IOTDeviceModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDeviceUseCase  @Inject constructor(
    private val repo: TuyaRepository
) : BaseUseCaseFlow<List<IOTDeviceModel>, Void>() {
    override suspend fun build(): Flow<ResultData<List<IOTDeviceModel>>> = repo.getDeviceList()
}