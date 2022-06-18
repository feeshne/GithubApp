package com.wentao.githubapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wentao.githubapp.network.NetworkManager
import com.wentao.githubapp.network.Resource
import com.wentao.githubapp.ui.BaseViewModel
import com.wentao.githubapp.ui.profile.model.User
import com.wentao.githubapp.ui.profile.model.UserRepository

class ProfileViewModel : BaseViewModel() {

    companion object {
        val name = "feeshne"
        val pass = "123456"
    }

    var isLogin: Boolean = false

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun checkUser(name: String, pass: String): Boolean {
        return name == ProfileViewModel.name && pass == ProfileViewModel.pass
    }

    val user: MutableLiveData<Resource<User>> =
        UserRepository(NetworkManager().getApiService()).getUser("feeshne")

}