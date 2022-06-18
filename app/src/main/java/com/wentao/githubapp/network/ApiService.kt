package com.wentao.githubapp.network

import com.wentao.githubapp.ui.home.model.EventResponse
import com.wentao.githubapp.ui.profile.model.User
import com.wentao.githubapp.ui.search.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): Call<RepoSearchResponse>

    @GET("events")
    fun getEvents(): Call<List<EventResponse>>
}