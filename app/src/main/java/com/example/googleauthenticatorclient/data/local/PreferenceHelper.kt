package com.example.googleauthenticatorclient.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.googleauthenticatorclient.utilities.Constants

class PreferenceHelper(context: Context) {

    private val preference: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE)

    fun getSharedKey() : String{
        return preference.getString(Constants.SHARED_KEY,"")
    }

    fun setSharedKey(key: String){
        preference.edit().putString(Constants.SHARED_KEY,key).apply()
    }
}