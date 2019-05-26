package com.example.googleauthenticatorclient.data

import com.example.googleauthenticatorclient.data.local.PreferenceHelper
import javax.inject.Inject


class Repository (private val prefHelper: PreferenceHelper){

    fun getSharedKey() : String{
        return prefHelper.getSharedKey()
    }

    fun setSharedKey(key: String){
        prefHelper.setSharedKey(key)
    }
}