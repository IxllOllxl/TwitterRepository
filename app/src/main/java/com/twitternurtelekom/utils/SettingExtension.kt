package com.twitternurtelekom.utils

import android.content.Context
import android.preference.PreferenceManager

fun Context.setToken(token: String?) {
    PreferenceManager.getDefaultSharedPreferences(this).edit().apply {
        putString(PREF_TOKEN_KEY, token)
    }.apply()
}

fun Context.getToken(): String {
    return PreferenceManager
            .getDefaultSharedPreferences(this)
            .getString(PREF_TOKEN_KEY, "")
}

const val PREF_TOKEN_KEY = "token"