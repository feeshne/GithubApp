package com.wentao.githubapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.wentao.githubapp.network.NetworkManager
import com.wentao.githubapp.network.Resource
import com.wentao.githubapp.ui.BaseViewModel
import com.wentao.githubapp.ui.home.model.EventResponse
import com.wentao.githubapp.ui.home.model.HomeRepository

class HomeViewModel : BaseViewModel() {

    fun getEvents(): MutableLiveData<Resource<List<EventResponse>>> {
        return HomeRepository(NetworkManager().getApiService()).getEvents()
    }
}