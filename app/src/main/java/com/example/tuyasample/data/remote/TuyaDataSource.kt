package com.example.tuyasample.data.remote

import com.example.tuyasample.data.common.ResultData
import com.example.tuyasample.domain.model.IOTDeviceModel
import com.thingclips.smart.android.user.api.ILoginCallback
import com.thingclips.smart.android.user.bean.User
import com.thingclips.smart.home.sdk.ThingHomeSdk
import com.thingclips.smart.home.sdk.bean.HomeBean
import com.thingclips.smart.home.sdk.callback.IThingHomeResultCallback
import com.thingclips.smart.sdk.api.IResultCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class TuyaDataSource @Inject constructor() {
    suspend fun login(): Boolean = suspendCancellableCoroutine { continuation ->
        ThingHomeSdk.getUserInstance().loginWithEmail(
            "", // Country code
            "", // Email
            "", // Password
            object : ILoginCallback {
                override fun onSuccess(user: User?) {
                    continuation.resume(true, {}) // Resume with true on success
                }

                override fun onError(code: String, error: String) {
                    continuation.resume(false, {}) // Resume with false on error
                }
            }
        )
    }

    fun getDeviceList(): Flow<ResultData<List<IOTDeviceModel>>> = callbackFlow {
        ThingHomeSdk.newHomeInstance(222705068).getHomeDetail(object : IThingHomeResultCallback {
            override fun onSuccess(bean: HomeBean) {
                trySend(ResultData.Success(bean.deviceList.map {
                    val device = ThingHomeSdk.getDataInstance().getDps(it.devId)
                    IOTDeviceModel(
                        name = it.getName(),
                        id = it.devId,
                        isOn = (device?.get("1") ?: false) as Boolean
                    )
                }))
            }

            override fun onError(errorCode: String?, errorMsg: String?) {
                trySend(
                    ResultData.Error(
                        code = errorCode ?: "00",
                        message = errorMsg ?: "Unexpected Error"
                    )
                )
            }
        })
        awaitClose { cancel() }
    }

    suspend fun publishDps(devId: String, value: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            val device = ThingHomeSdk.newDeviceInstance(devId)
            device.publishDps(value, object : IResultCallback {
                override fun onError(code: String?, error: String?) {
                    continuation.resume(false, {})
                }

                override fun onSuccess() {
                    continuation.resume(true, {})
                }
            })
        }
}