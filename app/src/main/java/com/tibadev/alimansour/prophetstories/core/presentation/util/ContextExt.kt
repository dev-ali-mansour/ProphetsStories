package com.tibadev.alimansour.prophetstories.core.presentation.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.tibadev.alimansour.prophetstories.R

fun Context.openLink(url: String) {
    try {
        val uri = url.toUri()
        val intentBuilder = CustomTabsIntent.Builder()
        val params = CustomTabColorSchemeParams.Builder()
            /*.setNavigationBarColor(ContextCompat.getColor(this, R.color.green_500))
            .setToolbarColor(ContextCompat.getColor(this, R.color.green_500))
            .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.green_500))*/
            .build()
        intentBuilder.apply {
            setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params)
            setStartAnimations(
                this@openLink,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
            setExitAnimations(
                this@openLink,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }
        val customTabsIntent = intentBuilder.build()
        customTabsIntent.launchUrl(this, uri)
    } catch (ex: Exception) {
        Toast.makeText(this, ex.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}


fun Context.appShare() {
    runCatching {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
        val builder = kotlin.text.StringBuilder("${getString(R.string.share_app_text)}\n")
        builder.append(getString(R.string.app_url))
        builder.append(packageName)
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            builder.toString()
        )
        startActivity(
            Intent.createChooser(
                sendIntent,
                getString(R.string.share)
            )
        )
    }.onFailure { t -> t.printStackTrace() }
}

fun Context.appRate() {
    runCatching {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                "${getString(R.string.app_market_url)}$packageName".toUri()
            )
        )
    }.onFailure { t ->
        when (t) {
            is ActivityNotFoundException ->
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        "${getString(R.string.app_url)}$packageName".toUri()
                    )
                )

            else -> t.printStackTrace()
        }
    }
}

fun Context.contactUs() {
    runCatching {
        val supportMail = arrayOf(getString(R.string.support_email))
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, supportMail)
            putExtra(
                Intent.EXTRA_SUBJECT,
                "${getString(R.string.app_name)} - ${getString(R.string.contact_us)}"
            )
        }
        startActivity(intent)
    }.onFailure { t ->
        when (t) {
            is ActivityNotFoundException ->
                Toast.makeText(
                    applicationContext,
                    getString(R.string.no_email_client),
                    Toast.LENGTH_SHORT
                ).show()

            else -> t.printStackTrace()
        }
    }
}

fun Context.reportError() {
    runCatching {
        val pInfo: PackageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
        else packageManager.getPackageInfo(packageName, 0)
        val builder =
            kotlin.text.StringBuilder("${getString(R.string.app_version)}${pInfo.versionName}\n")
        builder.append("${getString(R.string.android_version)}${Build.VERSION.SDK_INT}\n")
        builder.append("${getString(R.string.enter_your_phone)}\n\n")
        builder.append("${getString(R.string.input_error_details)}\n")
        val supportMail = arrayOf(getString(R.string.support_email))

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, supportMail)
            putExtra(
                Intent.EXTRA_SUBJECT,
                "${getString(R.string.app_name)} - ${getString(R.string.report_us)}"
            )
            putExtra(Intent.EXTRA_TEXT, builder.toString())
        }
        startActivity(intent)
    }.onFailure { t ->
        when (t) {
            is ActivityNotFoundException -> Toast.makeText(
                applicationContext,
                getString(R.string.no_email_client),
                Toast.LENGTH_SHORT
            ).show()

            else -> t.printStackTrace()
        }
    }
}

fun Context.showOurApps() {
    runCatching {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                getString(R.string.our_apps_market_url).toUri()
            )
        )
    }.onFailure { t ->
        when (t) {
            is ActivityNotFoundException ->
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        getString(R.string.our_apps_url).toUri()
                    )
                )

            else -> t.printStackTrace()
        }
    }
}