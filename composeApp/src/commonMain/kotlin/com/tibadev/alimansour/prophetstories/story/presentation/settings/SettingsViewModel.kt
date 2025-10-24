package com.tibadev.alimansour.prophetstories.story.presentation.settings

import androidx.lifecycle.ViewModel
import com.tibadev.alimansour.prophetstories.core.presentation.util.Constants
import com.tibadev.alimansour.prophetstories.core.util.Strings
import com.tibadev.alimansour.prophetstories.story.domain.model.SettingsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SettingsViewModel() : ViewModel() {

    private val _settingsFlow = MutableStateFlow<List<SettingsItem>>(listOf())
    val settingsFlow: StateFlow<List<SettingsItem>> get() = _settingsFlow

    fun getSettingsItems() {

        val items = arrayListOf(
            SettingsItem(
                Constants.SHARE_CODE,
                Strings.share_app
            ),
            SettingsItem(
                Constants.RATE_CODE,
                Strings.app_rate
            ),
            SettingsItem(
                Constants.CONTACT_CODE,
                Strings.contact_us
            ),
            SettingsItem(
                Constants.ERROR_REPORTING_CODE,
                Strings.report_us
            ),
            SettingsItem(
                Constants.APPS_CODE,
                Strings.our_apps
            ),
            SettingsItem(
                Constants.POLICY_CODE,
                Strings.privacy_policy
            ),
            SettingsItem(
                Constants.ABOUT_CODE,
                Strings.about_us
            ),
        )
        _settingsFlow.value = items
    }
}