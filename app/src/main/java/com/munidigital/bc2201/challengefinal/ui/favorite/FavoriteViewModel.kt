package com.munidigital.bc2201.challengefinal.ui.favorite


import android.app.Application
import androidx.lifecycle.*
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.database.getDataBase
import com.munidigital.bc2201.challengefinal.database.MainRepository
import kotlinx.coroutines.launch


class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val database= getDataBase(application)
    private val repository= MainRepository(database)

    val teamListLiveData=repository.favoriteList


    fun setFavoriteData(favoriteItem: FavoriteTeam) {
        viewModelScope.launch {
            repository.setFavoriteData(favoriteItem)
        }

    }


}






