package com.example.assignment3_colormaker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val INITIAL_RED_TEXT_VALUE = 0.0
const val INITIAL_GREEN_TEXT_VALUE = 0.0
const val INITIAL_BLUE_TEXT_VALUE = 0.0
const val INITIAL_RED_SWITCH_VALUE = true
const val INITIAL_GREEN_SWITCH_VALUE = true
const val INITIAL_BLUE_SWITCH_VALUE = true


class ColorMakerViewModel: ViewModel() {


    private var redText: Float = INITIAL_RED_TEXT_VALUE.toFloat()
    private var greenText: Float = INITIAL_GREEN_TEXT_VALUE.toFloat()
    private var blueText: Float = INITIAL_BLUE_TEXT_VALUE.toFloat()

    private var redSwitch: Boolean = INITIAL_RED_SWITCH_VALUE
    private var greenSwitch: Boolean = INITIAL_GREEN_SWITCH_VALUE
    private var blueSwitch: Boolean = INITIAL_BLUE_SWITCH_VALUE

   // var redText:Float = 22.8f

    override fun onCleared() {
        super.onCleared()
        Log.d("MainActivity", "OnCleared of CounterViewActivity called")
    }

    private val prefs = ColorMakerPreferencesDataStore.getRepository()

   private fun saveText(editText: Float, color: String) {
        Log.i(LOG_TAG, "In saveText method: -  $editText")
        viewModelScope.launch {
            prefs.saveText(editText,color)
            Log.d(LOG_TAG, "Saving the $color text: $editText")
        }
    }

   private fun saveSwitch(switchVal: Boolean , color: String) {
        Log.i(LOG_TAG, "In switchValue method: -  $switchVal")
        viewModelScope.launch {
            prefs.saveSwitch(switchVal,color)
            Log.d(LOG_TAG, "Saving the $color switchValue: $switchVal")
        }
    }

    fun loadRedText() {
        viewModelScope.launch {
            prefs.red_text.collectLatest {
                redText = it
                Log.d(LOG_TAG, "Loaded the red TExt from DataStore: $redText")
            }
        }
        viewModelScope.launch {
            prefs.green_text.collectLatest {
                greenText = it
                Log.d(LOG_TAG, "Loaded the green TExt from DataStore: $greenText")
            }
        }
        viewModelScope.launch {
            prefs.blue_text.collectLatest {
                blueText = it
                Log.d(LOG_TAG, "Loaded the blue TExt from DataStore: $blueText")
            }
        }
        viewModelScope.launch {
            prefs.red_switch.collectLatest {
                redSwitch = it
                Log.d(LOG_TAG, "Loaded the red Switch from DataStore: $redSwitch")
            }
        }

        viewModelScope.launch {
            prefs.green_switch.collectLatest {
                greenSwitch = it
                Log.d(LOG_TAG, "Loaded the green Switch from DataStore: $greenSwitch")
            }
        }
        viewModelScope.launch {
            prefs.blue_switch.collectLatest {
                blueSwitch = it
                Log.d(LOG_TAG, "Loaded the blue Switch from DataStore: $blueSwitch")
            }
        }

        Thread.sleep(1000)
    }

    fun getRedTextValue(): Float {
        Log.d(LOG_TAG, "getRedTextValue method : Counter this value ${this.redText} and counter is $redText")
        return this.redText
    }

    fun setRedTextValue(c: Float) {
        this.redText = c
        val color = "red"
        saveText(redText, color)
    }

    fun getGreenTextValue(): Float {
        Log.d(LOG_TAG, "getGreenTextValue method : Counter this value ${this.greenText} and counter is $greenText")
        return this.greenText
    }

    fun setGreenTextValue(c: Float) {
        this.greenText = c
        val color = "green"
        saveText(greenText, color)
//        saveGreenText()
    }

    fun getBlueTextValue(): Float {
        Log.d(LOG_TAG, "getBlueTextValue method : Counter this value ${this.blueText} and counter is $blueText")
        return this.blueText
    }

    fun setBlueTextValue(c: Float) {
        this.blueText = c
        val color = "blue"
        saveText(blueText, color)
//        saveBlueText()

    }

    fun getRedSwitchValue(): Boolean{
        Log.d(LOG_TAG, "getRedSwitchValue method : switch this value ${this.redSwitch} and counter is $redSwitch")
        return this.redSwitch
    }
    fun setRedSwitchValue(switchValue: Boolean){
        this.redSwitch  = switchValue
        val color = "red"
        saveSwitch(redSwitch,color)
    }
    fun getGreenSwitchValue(): Boolean {
        Log.d(
            LOG_TAG,
            "getGreenSwitchValue method : switch this value ${this.greenSwitch} and counter is $greenSwitch"
        )
        return this.greenSwitch
    }
    fun setGreenSwitchValue(switchValue: Boolean){
        this.greenSwitch  = switchValue
        val color = "green"
        saveSwitch(greenSwitch,color)
    }


    fun getBlueSwitchValue(): Boolean {
        Log.d(
            LOG_TAG,
            "getBlueSwitchValue method : switch this value ${this.blueSwitch} and counter is $blueSwitch"
        )
        return this.blueSwitch
    }
    fun setBlueSwitchValue(switchValue: Boolean){
        this.blueSwitch  = switchValue
        val color = "blue"
        saveSwitch(blueSwitch,color)
    }
}