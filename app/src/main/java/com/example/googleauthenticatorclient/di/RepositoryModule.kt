package com.example.googleauthenticatorclient.di

import android.content.Context
import com.example.googleauthenticatorclient.data.Repository
import com.example.googleauthenticatorclient.data.local.PreferenceHelper
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule(private val context: Context) {

    @Provides
    fun getPrefHelper():PreferenceHelper{
        return PreferenceHelper(context)
    }

    @Provides
    fun getRepository(preferenceHelper: PreferenceHelper):Repository{
        return Repository(preferenceHelper)
    }
}