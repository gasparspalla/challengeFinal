package com.munidigital.bc2201.challengefinal.ui.favorite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.munidigital.bc2201.challengefinal.ui.home.HomeViewModel

class FavoriteViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(application) as T
    }
}