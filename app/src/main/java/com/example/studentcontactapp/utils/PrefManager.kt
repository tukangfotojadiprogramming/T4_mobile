package com.example.studentcontactapp.utils

import android.content.Context

class PrefManager(context: Context) {

    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun setLogin(isLogin: Boolean, username: String) {
        editor.putBoolean("isLogin", isLogin)
        editor.putString("username", username)
        editor.apply()
    }

    fun isLogin(): Boolean {
        return prefs.getBoolean("isLogin", false)
    }

    fun getUsername(): String? {
        return prefs.getString("username", "")
    }

    fun setRememberMe(value: Boolean) {
        editor.putBoolean("remember", value)
        editor.apply()
    }

    fun isRememberMe(): Boolean {
        return prefs.getBoolean("remember", false)
    }

    fun logout() {
        editor.clear()
        editor.apply()
    }
}