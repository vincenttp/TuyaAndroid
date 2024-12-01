package com.example.tuyasample.domain.usecase

import com.example.tuyasample.data.common.ResultData
import com.example.tuyasample.data.repository.TuyaRepository
import javax.inject.Inject

class LoginTuyaUseCase @Inject constructor(
    private val repo: TuyaRepository
) : BaseUseCase<Boolean, Void>() {
    override suspend fun build(params: Void?): ResultData<Boolean> = repo.login()
}