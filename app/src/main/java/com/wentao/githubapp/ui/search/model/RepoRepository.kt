package com.wentao.githubapp.ui.search.model

import com.wentao.githubapp.network.ApiService
import com.wentao.githubapp.network.NetworkCall

class RepoRepository(var apiServices: ApiService) {

    fun getRepos(key: String) =
        NetworkCall<RepoSearchResponse>().makeCall(apiServices.searchRepos(key))
}