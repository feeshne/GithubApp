package com.wentao.githubapp.ui.home.model

import com.wentao.githubapp.network.ApiService
import com.wentao.githubapp.network.NetworkCall

class HomeRepository(var apiServices: ApiService) {

    fun getEvents() =
        NetworkCall<List<EventResponse>>().makeCall(apiServices.getEvents())
}