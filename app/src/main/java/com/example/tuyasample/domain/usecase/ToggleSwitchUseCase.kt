package com.example.tuyasample.domain.usecase

import com.example.tuyasample.data.common.ResultData
import com.example.tuyasample.data.repository.TuyaRepository
import javax.inject.Inject

class ToggleSwitchUseCase@Inject constructor(
    private val repo: TuyaRepository
) : BaseUseCase<Boolean, ToggleSwitchUseCase.Param>() {

    data class Param(val devId: String, val value: Boolean)

    override suspend fun build(params: Param?): ResultData<Boolean> = repo.toggleSwitch(
        devId = params?.devId.orEmpty(),
        param = "{\"1\":${params?.value ?: false}}"
    )
}