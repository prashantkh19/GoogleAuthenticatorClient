package com.example.googleauthenticatorclient.di

import com.example.googleauthenticatorclient.data.Repository
import com.example.googleauthenticatorclient.ui.HomeActivity
import com.example.googleauthenticatorclient.ui.NewAccountActivity
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface HomeActivityComponent{
    fun init(activity: HomeActivity)
    fun init(activity: NewAccountActivity)
}