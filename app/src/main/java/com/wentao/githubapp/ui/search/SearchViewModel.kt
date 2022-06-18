package com.wentao.githubapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wentao.githubapp.ui.BaseViewModel

class SearchViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search Fragment"
    }
    val text: LiveData<String> = _text
}