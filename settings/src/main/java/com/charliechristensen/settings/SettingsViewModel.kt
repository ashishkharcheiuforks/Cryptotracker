package com.charliechristensen.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.charliechristensen.cryptotracker.common.AppTheme
import com.charliechristensen.cryptotracker.common.BaseViewModel
import com.charliechristensen.cryptotracker.common.Constants
import com.charliechristensen.cryptotracker.common.SingleLiveEvent
import com.charliechristensen.cryptotracker.data.preferences.AppPreferences
import javax.inject.Inject
import kotlinx.coroutines.flow.map

/**
 * Settings ViewModel
 */
interface SettingsViewModel {

    interface Inputs {
        fun themeButtonClicked()
        fun currencyButtonClicked()
        fun liveUpdatePricesToggled(isChecked: Boolean)
        fun themeChosen(theme: AppTheme)
        fun setCurrency(symbol: String)
    }

    interface Outputs {
        val themeDisplay: LiveData<Int>
        val liveUpdatePrices: LiveData<Boolean>
        val displayCurrency: LiveData<String>
        val showChooseThemeDialog: LiveData<List<AppTheme>>
        val showCurrencyDialog: LiveData<Array<String>>
    }

    class ViewModel @Inject constructor(private val appPreferences: AppPreferences) :
        BaseViewModel(), Inputs, Outputs {

        private val showChooseThemeEvent = SingleLiveEvent<List<AppTheme>>()
        private val showCurrencyDialogEvent = SingleLiveEvent<Array<String>>()

        val inputs: Inputs = this
        val outputs: Outputs = this

        //region Inputs

        override fun themeButtonClicked() {
            showChooseThemeEvent.value = Constants.availableThemes
        }

        override fun currencyButtonClicked() {
            showCurrencyDialogEvent.value = Constants.availableCurrencies
        }

        override fun liveUpdatePricesToggled(isChecked: Boolean) {
            appPreferences.setLiveUpdatePrices(isChecked)
        }

        override fun themeChosen(theme: AppTheme) {
            appPreferences.setTheme(theme)
        }

        override fun setCurrency(symbol: String) {
            appPreferences.setCurrency(symbol)
        }

        //endregion

        //region Outputs

        override val themeDisplay: LiveData<Int> = appPreferences.theme()
            .map { it.displayId }
            .asLiveData()

        override val liveUpdatePrices: LiveData<Boolean> = appPreferences
            .liveUpdatePrices()
            .asLiveData()

        override val displayCurrency: LiveData<String> = appPreferences
            .currency()
            .asLiveData()

        override val showChooseThemeDialog: LiveData<List<AppTheme>> = showChooseThemeEvent

        override val showCurrencyDialog: LiveData<Array<String>> = showCurrencyDialogEvent

        //endregion

    }
}
