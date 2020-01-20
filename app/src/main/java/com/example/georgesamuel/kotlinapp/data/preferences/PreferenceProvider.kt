package com.example.georgesamuel.kotlinapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val PREF_KEY_SAVED_AT = "PREF_KEY_SAVED_AT"

@Suppress("DEPRECATION")
class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: String) {
        preference.edit().putString(PREF_KEY_SAVED_AT, savedAt).apply()
    }

    fun getLastSavedAt():String? {
        return preference.getString(PREF_KEY_SAVED_AT, null)
    }
}