package com.wentao.githubapp.ui.profile.model

import com.wentao.githubapp.network.ApiService
import com.wentao.githubapp.network.NetworkCall

class UserRepository(var apiServices: ApiService) {

    fun getUser(userID: String) = NetworkCall<User>().makeCall(apiServices.getUser(userID))
}