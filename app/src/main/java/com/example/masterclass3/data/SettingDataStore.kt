package com.example.masterclass3.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val APP_SETTING = "app_setting"
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = APP_SETTING )
class SettingDataStore(private val context: Context) {

    private val LAYOUT_MANAGER = booleanPreferencesKey("LAYOUT_MANAGER")

    suspend fun saveOrientation(orn:Boolean){
        context.dataStore.edit {
            it[LAYOUT_MANAGER] = orn
        }
    }

    val orientation: Flow<Boolean> = context.dataStore.data.map {
        it[LAYOUT_MANAGER]?:false
    }

}