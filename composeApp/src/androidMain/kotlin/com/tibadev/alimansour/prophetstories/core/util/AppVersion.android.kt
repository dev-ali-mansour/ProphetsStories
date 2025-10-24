package com.tibadev.alimansour.prophetstories.core.util

import android.content.Context

private lateinit var versionContext: Context

fun initializeVersionContext(context: Context) {
    versionContext = context.applicationContext
}

actual fun getAppVersion(): String {
    return try {
        versionContext.packageManager.getPackageInfo(versionContext.packageName, 0).versionName ?: "2.5.4"
    } catch (e: Exception) {
        "2.5.4"
    }
}

