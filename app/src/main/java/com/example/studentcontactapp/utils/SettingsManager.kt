package com.example.studentcontactapp.utils

import android.content.Context

class SettingsManager(context: Context) {

    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun setDarkMode(value: Boolean) {
        editor.putBoolean("dark_mode", value).apply()
    }

    fun isDarkMode(): Boolean {
        return prefs.getBoolean("dark_mode", false)
    }

    fun setFontSize(size: Int) {
        editor.putInt("font_size", size).apply()
    }

    fun getFontSize(): Int {
        return prefs.getInt("font_size", 16)
    }

    fun setNotification(value: Boolean) {
        editor.putBoolean("notif", value).apply()
    }

    fun isNotification(): Boolean {
        return prefs.getBoolean("notif", true)
    }
}