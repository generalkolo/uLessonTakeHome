package com.gokada.ng.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gokada.core.base.BaseViewModel
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(
) : BaseViewModel() {

    private val _loggedInUserLiveData = MutableLiveData<Int>()
    val loggedInUserLiveData = _loggedInUserLiveData as LiveData<Int>
}