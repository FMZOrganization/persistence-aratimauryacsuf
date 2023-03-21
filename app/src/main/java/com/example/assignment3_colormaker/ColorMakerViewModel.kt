package com.example.assignment3_colormaker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val INITIAL_REDTEXT_VALUE = 0.0
const val INITIAL_GREEN_TEXT_VALUE = 0.0
const val INITIAL_BLUE_TEXT_VALUE = 0.0
const val INITIAL_RED_SWITCH_VALUE = true
const val INITIAL_GREEN_SWITCH_VALUE = true
const val INITIAL_BLUE_SWITCH_VALUE = true


class ColorMakerViewModel: ViewModel() {


    private var redText: Float = INITIAL_REDTEXT_VALUE.toFloat()
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
    fun saveRedText() {
        Log.i(LOG_TAG, "In saveRedText method: -  $redText")
        viewModelScope.launch {
            prefs.saveRedText(redText)
            Log.d(LOG_TAG, "Saving the red text: $redText")
        }
    }
    fun saveGreenText() {
        Log.i(LOG_TAG, "In saveGreenText method: -  ${greenText}")
        viewModelScope.launch {
            prefs.saveGreenText(greenText)
            Log.d(LOG_TAG, "Saving the green text: $greenText")
        }
    }
    fun saveBlueText() {
        Log.i(LOG_TAG, "In saveBlueText method: -  ${blueText}")
        viewModelScope.launch {
            prefs.saveBlueText(blueText)
            Log.d(LOG_TAG, "Saving the  textblue: $blueText")
        }
    }

    fun saveRedSwitch(){
        Log.i(LOG_TAG, "In saveRedSwitch method: -  ${redSwitch}")
        viewModelScope.launch {
            prefs.saveRedSwitch(redSwitch)
            Log.d(LOG_TAG, "Saving the  redSwitch : $redSwitch")
        }
    }
    fun saveGreenSwitch(){
        Log.i(LOG_TAG, "In saveGreenSwitch method: -  ${greenSwitch}")
        viewModelScope.launch {
            prefs.saveGreenSwitch(greenSwitch)
            Log.d(LOG_TAG, "Saving the  greenSwitch : $greenSwitch")
        }
    }fun saveBlueSwitch(){
        Log.i(LOG_TAG, "In saveBlueSwitch method: -  ${blueSwitch}")
        viewModelScope.launch {
            prefs.saveBlueSwitch(blueSwitch)
            Log.d(LOG_TAG, "Saving the  blueSwitch : $blueSwitch")
        }
    }


    fun loadRedText() {
        GlobalScope.launch {
            prefs.red_text.collectLatest {
                redText = it
                Log.d(LOG_TAG, "Loaded the red TExt from DataStore: $redText")
            }
        }
        GlobalScope.launch {
            prefs.green_text.collectLatest {
                greenText = it
                Log.d(LOG_TAG, "Loaded the green TExt from DataStore: $greenText")
            }
        }
        GlobalScope.launch {
            prefs.blue_text.collectLatest {
                blueText = it
                Log.d(LOG_TAG, "Loaded the blue TExt from DataStore: $blueText")
            }
        }
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

        Thread.sleep(1000)
    }

    fun getRedTextValue(): Float {
        Log.d(LOG_TAG, "getRedTextValue method : Counter this value ${this.redText} and counter is $redText")
        return this.redText
    }

    fun setRedTextValue(c: Float) {
        this.redText = c
        saveRedText()
    }

    fun getGreenTextValue(): Float {
        Log.d(LOG_TAG, "getGreenTextValue method : Counter this value ${this.greenText} and counter is $greenText")
        return this.greenText
    }

    fun setGreenTextValue(c: Float) {
        this.greenText = c
        saveGreenText()
    }

    fun getBlueTextValue(): Float {
        Log.d(LOG_TAG, "getBlueTextValue method : Counter this value ${this.blueText} and counter is $blueText")
        return this.blueText
    }

    fun setBlueTextValue(c: Float) {
        this.blueText = c
        saveBlueText()

    }

    fun getRedSwitchValue(): Boolean{
        Log.d(LOG_TAG, "getRedSwitchValue method : switch this value ${this.redSwitch} and counter is $redSwitch")
        return this.redSwitch
    }
    fun setRedSwitchValue(switchValue: Boolean){
        this.redSwitch  = switchValue
        saveRedSwitch()
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
        saveGreenSwitch()
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
        saveBlueSwitch()
    }
}