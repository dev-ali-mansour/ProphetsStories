package com.tibadev.alimansour.prophetstories.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.tibadev.alimansour.prophetstories.R

class Network (private val context: Context){
    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun openLink(strLink: String) {
        try {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary)).setShowTitle(true)
            builder.setStartAnimations(context, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
            builder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(strLink))
        } catch (ex: Exception) {
            Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}