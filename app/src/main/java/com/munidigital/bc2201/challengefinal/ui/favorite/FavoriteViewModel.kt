package com.munidigital.bc2201.challengefinal.ui.favorite


import android.app.Application
import androidx.lifecycle.*
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.api.ApiResponseStatus
import com.munidigital.bc2201.challengefinal.database.getDataBase
import com.munidigital.bc2201.challengefinal.ui.home.MainRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException


class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val database= getDataBase(application)
    private val repository= MainRepository(database)

    val teamListLiveData=repository.favoriteList


    fun setValue(favoriteItem: FavoriteTeam) {
        getFavoriteData(favoriteItem)
    }

    private fun getFavoriteData(itemFavorite: FavoriteTeam) {
        viewModelScope.launch {
            repository.getFavoriteData(itemFavorite)
        }
    }

}






