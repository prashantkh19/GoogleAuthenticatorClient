package com.example.googleauthenticatorclient.ui.viewModel

import android.arch.lifecycle.ViewModel
import com.example.googleauthenticatorclient.data.Repository
import com.warrenstrange.googleauth.GoogleAuthenticator


public class HomeActivityViewModel(private val repository: Repository): ViewModel() {

    init{
        System.setProperty("com.warrenstrange.googleauth.rng.algorithmProvider", "AndroidOpenSSL")
    }
    private val gAuth = GoogleAuthenticator()

    private var status :Boolean ?=null

    fun isKeySet():Boolean{
        return repository.getSharedKey().isEmpty().not()
    }

    fun verifyCode(code: Int){
        status = gAuth.authorize(repository.getSharedKey(),code)
    }

    fun getStatus():Boolean? = status
}
