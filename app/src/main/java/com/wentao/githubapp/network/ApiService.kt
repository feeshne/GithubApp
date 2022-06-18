package com.wentao.githubapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wentao.githubapp.ui.profile.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>
}