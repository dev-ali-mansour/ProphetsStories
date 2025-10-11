package com.tibadev.alimansour.prophetstories.core.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openUrl(url: String) {
    val nsUrl = NSURL(string = url)
    if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}

actual fun shareApp(text: String, url: String) {
    // iOS share requires a view controller, implement when needed
    println("Share: $text\n$url")
}

actual fun rateApp(marketUrl: String, fallbackUrl: String) {
    openUrl(marketUrl)
}

actual fun sendEmail(email: String, subject: String, body: String?) {
    val mailUrl = if (body != null) {
        "mailto:$email?subject=$subject&body=$body"
    } else {
        "mailto:$email?subject=$subject"
    }
    openUrl(mailUrl)
}

actual fun showOurApps(marketUrl: String, fallbackUrl: String) {
    openUrl(marketUrl)
}

