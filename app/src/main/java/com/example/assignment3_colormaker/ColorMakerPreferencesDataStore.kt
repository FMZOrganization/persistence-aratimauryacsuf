package com.example.assignment3_colormaker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ColorMakerPreferencesDataStore private constructor(private val dataStore: DataStore<Preferences>) {

    private val RED_TEXT_KEY = floatPreferencesKey("red_text")
   private val GREEN_TEXT_KEY = floatPreferencesKey("green_text")
  private val BLUE_TEXT_KEY = floatPreferencesKey("blue_text")

    val green_text: Flow<Float> = this.dataStore.data.map { prefs ->
        prefs[GREEN_TEXT_KEY] ?: INITIAL_GREEN_TEXT_VALUE
    }.distinctUntilChanged() as Flow<Float>

    val red_text: Flow<Float> = this.dataStore.data.map { prefs ->
        prefs[RED_TEXT_KEY] ?: INITIAL_REDTEXT_VALUE
    }.distinctUntilChanged() as Flow<Float>

    val blue_text: Flow<Float> = this.dataStore.data.map { prefs ->
        prefs[BLUE_TEXT_KEY] ?: INITIAL_BLUE_TEXT_VALUE
    }.distinctUntilChanged() as Flow<Float>


    private suspend fun saveFloatValue(key: Preferences.Key<Float>, value: Float) {
        this.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    suspend fun saveRedText(value: Float) {
        saveFloatValue(RED_TEXT_KEY, value)
    }
    suspend fun saveGreenText(value: Float) {
        saveFloatValue(GREEN_TEXT_KEY, value)
    }
    suspend fun saveBlueText(value: Float) {
        saveFloatValue(BLUE_TEXT_KEY, value)
    }

    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "settings"
        private var INSTANCE: ColorMakerPreferencesDataStore? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                INSTANCE = ColorMakerPreferencesDataStore(dataStore)
            }
        }
        fun getRepository(): ColorMakerPreferencesDataStore {
            return INSTANCE ?: throw IllegalStateException("AppPreferencesRepository not initialized yet")
        }
    }
}