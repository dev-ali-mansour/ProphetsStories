package com.tibadev.alimansour.prophetstories.core.util

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

private lateinit var appContext: android.content.Context

fun initializePlatformContext(context: android.content.Context) {
    appContext = context.applicationContext
}

actual fun openUrl(url: String) {
    try {
        val uri = url.toUri()
        val intentBuilder = CustomTabsIntent.Builder()
        val params = CustomTabColorSchemeParams.Builder().build()
        intentBuilder.apply {
            setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params)
            setStartAnimations(
                appContext,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
            setExitAnimations(
                appContext,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }
        val customTabsIntent = intentBuilder.build()
        customTabsIntent.launchUrl(appContext, uri)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

actual fun shareApp(text: String, url: String) {
    try {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "$text\n$url")
        }
        appContext.startActivity(
            Intent.createChooser(sendIntent, "Share").apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}

actual fun rateApp(marketUrl: String, fallbackUrl: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, marketUrl.toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        appContext.startActivity(intent)
    } catch (t: ActivityNotFoundException) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, fallbackUrl.toUri()).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            appContext.startActivity(intent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

actual fun sendEmail(email: String, subject: String, body: String?) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            body?.let { putExtra(Intent.EXTRA_TEXT, it) }
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        appContext.startActivity(intent)
    } catch (t: ActivityNotFoundException) {
        t.printStackTrace()
    }
}

actual fun showOurApps(marketUrl: String, fallbackUrl: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, marketUrl.toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        appContext.startActivity(intent)
    } catch (t: ActivityNotFoundException) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, fallbackUrl.toUri()).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            appContext.startActivity(intent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

