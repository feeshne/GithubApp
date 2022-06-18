package com.wentao.githubapp.ui.home.model

import com.google.gson.annotations.SerializedName

data class Payload (

    @SerializedName("ref"           ) var ref          : String? = null,
    @SerializedName("ref_type"      ) var refType      : String? = null,
    @SerializedName("master_branch" ) var masterBranch : String? = null,
    @SerializedName("description"   ) var description  : String? = null,
    @SerializedName("pusher_type"   ) var pusherType   : String? = null

)
