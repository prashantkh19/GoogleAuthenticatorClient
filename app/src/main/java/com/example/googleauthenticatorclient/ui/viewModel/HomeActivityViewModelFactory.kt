package com.example.googleauthenticatorclient.ui.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.googleauthenticatorclient.data.Repository
import dagger.internal.DaggerCollections
import javax.inject.Inject

class HomeActivityViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeActivityViewModel(repository) as T
    }

}