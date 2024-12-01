package com.example.tuyasample

import android.app.Application
import com.thingclips.smart.home.sdk.ThingHomeSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ThingHomeSdk.init(this)
        ThingHomeSdk.setDebugMode(true)
    }

}