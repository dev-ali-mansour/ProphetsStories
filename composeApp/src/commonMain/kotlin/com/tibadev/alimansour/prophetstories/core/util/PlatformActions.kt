package com.tibadev.alimansour.prophetstories.core.util

expect fun openUrl(url: String)
expect fun shareApp(text: String, url: String)
expect fun rateApp(marketUrl: String, fallbackUrl: String)
expect fun sendEmail(email: String, subject: String, body: String?)
expect fun showOurApps(marketUrl: String, fallbackUrl: String)

