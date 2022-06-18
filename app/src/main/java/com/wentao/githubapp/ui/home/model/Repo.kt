package com.wentao.githubapp.ui.home.model

import com.google.gson.annotations.SerializedName


data class Repo (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)
