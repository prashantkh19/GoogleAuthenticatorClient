package com.example.googleauthenticatorclient.ui.viewModel

import android.arch.lifecycle.ViewModel
import com.example.googleauthenticatorclient.data.Repository
import com.warrenstrange.googleauth.GoogleAuthenticator


public class NewAccountActivityViewModel(private val repository: Repository): ViewModel() {

    init{
        System.setProperty("com.warrenstrange.googleauth.rng.algorithmProvider", "AndroidOpenSSL")
    }
    private val gAuth = GoogleAuthenticator()

    fun setSharedKey(key:String){
        repository.setSharedKey(key)
    }

    fun verifyCode(code: Int):Boolean{
        return gAuth.authorize(repository.getSharedKey(),code)
    }
}
