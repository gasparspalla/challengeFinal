package com.munidigital.bc2201.challengefinal.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.munidigital.bc2201.challengefinal.api.ApiResponseStatus
import com.munidigital.bc2201.challengefinal.database.MainRepository
import com.munidigital.bc2201.challengefinal.database.getDataBase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val database= getDataBase(application)
    private val repository= MainRepository(database)

    val teamListLiveData=repository.teamList

    private val _statusLiveData= MutableLiveData<ApiResponseStatus>()
    val statusLiveData: LiveData<ApiResponseStatus>
        get() =_statusLiveData

    init {
        viewModelScope.launch {
            try {
                _statusLiveData.value = ApiResponseStatus.LOADING
                repository.fetchTeams()
                _statusLiveData.value = ApiResponseStatus.DONE
            }
            catch (e:UnknownHostException){
                if (teamListLiveData.value==null){
                    Log.e("No internet connection",e.toString())
                    _statusLiveData.value=ApiResponseStatus.NO_INTERNET_CONNECTION
                }
                else _statusLiveData.value = ApiResponseStatus.DONE

            }

        }

    }

}