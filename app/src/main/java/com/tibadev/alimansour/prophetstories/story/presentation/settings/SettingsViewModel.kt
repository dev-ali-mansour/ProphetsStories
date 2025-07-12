package com.tibadev.alimansour.prophetstories.story.presentation.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.util.Constants
import com.tibadev.alimansour.prophetstories.story.domain.model.SettingsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SettingsViewModel() : ViewModel() {

    private val _settingsFlow = MutableStateFlow<List<SettingsItem>>(listOf())
    val settingsFlow: StateFlow<List<SettingsItem>> get() = _settingsFlow

    fun getSettingsItems(context: Context) {

        val items = arrayListOf(
            SettingsItem(
                Constants.SHARE_CODE,
                context.getString(R.string.share)
            ),
            SettingsItem(
                Constants.RATE_CODE,
                context.getString(R.string.app_rate)
            ),
            SettingsItem(
                Constants.CONTACT_CODE,
                context.getString(R.string.contact_us)
            ),
            SettingsItem(
                Constants.ERROR_REPORTING_CODE,
                context.getString(R.string.report_us)
            ),
            SettingsItem(
                Constants.APPS_CODE,
                context.getString(R.string.our_apps)
            ),
            SettingsItem(
                Constants.POLICY_CODE,
                context.getString(R.string.privacy_policy)
            ),
            SettingsItem(
                Constants.ABOUT_CODE,
                context.getString(R.string.about_us)
            ),
        )
        _settingsFlow.value = items
    }
}