package com.example.assignment3_colormaker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import java.lang.Thread.sleep

const val INITIAL_RED_TEXT_VALUE = 0.0
const val INITIAL_GREEN_TEXT_VALUE = 0.0
const val INITIAL_BLUE_TEXT_VALUE = 0.0
const val INITIAL_RED_SWITCH_VALUE = true
const val INITIAL_GREEN_SWITCH_VALUE = true
const val INITIAL_BLUE_SWITCH_VALUE = true


class ColorMakerViewModel : ViewModel() {


    private var redText: Float = INITIAL_RED_TEXT_VALUE.toFloat()
    private var greenText: Float = INITIAL_GREEN_TEXT_VALUE.toFloat()
    private var blueText: Float = INITIAL_BLUE_TEXT_VALUE.toFloat()

    private var redSwitch: Boolean = INITIAL_RED_SWITCH_VALUE
    private var greenSwitch: Boolean = INITIAL_GREEN_SWITCH_VALUE
    private var blueSwitch: Boolean = INITIAL_BLUE_SWITCH_VALUE


    override fun onCleared() {
        super.onCleared()
        Log.d("MainActivity", "OnCleared of ColorMakerViewModel called")
    }

    private val prefs = ColorMakerPreferencesDataStore.getRepository()

    private fun saveText(editText: Float, color: String) {
        Log.i(LOG_TAG, "In saveText method: -  $editText")
        viewModelScope.launch {
            prefs.saveText(editText, color)
            Log.d(LOG_TAG, "Saving the $color text: $editText")
        }
    }

    private fun saveSwitch(switchVal: Boolean, color: String) {
        Log.i(LOG_TAG, "In switchValue method: -  $switchVal")
        viewModelScope.launch {
            prefs.saveSwitch(switchVal, color)
            Log.d(LOG_TAG, "Saving the $color switchValue: $switchVal")
        }
    }


    fun loadTextValues() {

        GlobalScope.launch {
            prefs.red_text.collectLatest {
                redText = it
                Log.d(LOG_TAG, "Loaded the red Text from DataStore: $redText")
            }
        }
        GlobalScope.launch {
            prefs.green_text.collectLatest {
                greenText = it
                Log.d(LOG_TAG, "Loaded the green Text from DataStore: $greenText")
            }
        }
        GlobalScope.launch {
            prefs.blue_text.collectLatest {
                blueText = it
                Log.d(LOG_TAG, "Loaded the blue Text from DataStore: $blueText")
            }
        }
        sleep(1000)


    }

    fun loadSwitchValues() {
        GlobalScope.launch {
            prefs.red_switch.collectLatest {
                redSwitch = it
                Log.d(LOG_TAG, "Loaded the red Switch from DataStore: $redSwitch")
            }
        }


        GlobalScope.launch {
            prefs.green_switch.collectLatest {
                greenSwitch = it
                Log.d(LOG_TAG, "Loaded the green Switch from DataStore: $greenSwitch")
            }
        }

        GlobalScope.launch {
            prefs.blue_switch.collectLatest {
                blueSwitch = it
                Log.d(LOG_TAG, "Loaded the blue Switch from DataStore: $blueSwitch")
            }
        }
        sleep(1000)
    }

    fun getRedTextValue(): Float {
        return this.redText
    }

    fun setRedTextValue(c: Float) {
        this.redText = c
        val color = "red"
        saveText(redText, color)
    }

    fun getGreenTextValue(): Float {
        return this.greenText
    }

    fun setGreenTextValue(c: Float) {
        this.greenText = c
        val color = "green"
        saveText(greenText, color)

    }

    fun getBlueTextValue(): Float {
        return this.blueText
    }

    fun setBlueTextValue(c: Float) {
        this.blueText = c
        val color = "blue"
        saveText(blueText, color)
    }

    fun getRedSwitchValue(): Boolean {

        return this.redSwitch
    }

    fun setRedSwitchValue(switchValue: Boolean) {
        this.redSwitch = switchValue
        val color = "red"
        saveSwitch(redSwitch, color)
    }

    fun getGreenSwitchValue(): Boolean {
        return this.greenSwitch
    }

    fun setGreenSwitchValue(switchValue: Boolean) {
        this.greenSwitch = switchValue
        val color = "green"
        saveSwitch(greenSwitch, color)
    }

    fun getBlueSwitchValue(): Boolean {
        return this.blueSwitch
    }

    fun setBlueSwitchValue(switchValue: Boolean) {
        this.blueSwitch = switchValue
        val color = "blue"
        saveSwitch(blueSwitch, color)
    }
}