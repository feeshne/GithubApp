package com.wentao.githubapp.ui.search

import androidx.lifecycle.MutableLiveData
import com.wentao.githubapp.network.NetworkManager
import com.wentao.githubapp.network.Resource
import com.wentao.githubapp.ui.BaseViewModel
import com.wentao.githubapp.ui.search.model.RepoRepository
import com.wentao.githubapp.ui.search.model.RepoSearchResponse

class SearchViewModel : BaseViewModel() {

    fun getSearchResult(key: String): MutableLiveData<Resource<RepoSearchResponse>> {
        return RepoRepository(NetworkManager().getApiService()).getRepos(key)
    }
}