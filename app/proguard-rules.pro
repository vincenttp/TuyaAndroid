# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# FastJSON (com.alibaba.fastjson)
-keep class com.alibaba.fastjson.** { *; }
-dontwarn com.alibaba.fastjson.**

# MQTT (com.thingclips.smart.mqttclient.mqttv3)
-keep class com.thingclips.smart.mqttclient.mqttv3.** { *; }
-dontwarn com.thingclips.smart.mqttclient.mqttv3.**

# OkHttp3
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class okio.** { *; }
-dontwarn okio.**

# Tuya SDK (com.thingclips)
-keep class com.thingclips.** { *; }
-dontwarn com.thingclips.**

# Matter SDK (chip)
-keep class chip.** { *; }
-dontwarn chip.**

# MINI SDK (com.gzl.smart)
-keep class com.gzl.smart.** { *; }
-dontwarn com.gzl.smart.**

# Keep public API classes for Tuya SDK
-keep class com.thingclips.smart.** { *; }
-dontwarn com.thingclips.smart.**

# Keep all classes for Tuya's Bluetooth SDK (if used)
-keep class com.thingclips.smart.thingsmartbluetooth.** { *; }
-dontwarn com.thingclips.smart.thingsmartbluetooth.**

#fastJson
-keep class com.alibaba.fastjson.**{*;}
-dontwarn com.alibaba.fastjson.**

#rx
-dontwarn rx.**
-keep class rx.** {*;}
-keep class io.reactivex.**{*;}
-dontwarn io.reactivex.**
-keep class rx.**{ *; }
-keep class rx.android.**{*;}

#fresco
-keep class com.facebook.drawee.backends.pipeline.Fresco
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
@com.facebook.common.internal.DoNotStrip *;
}

#tuya
-keep class com.thingclips.**{*;}
-dontwarn com.thingclips.**