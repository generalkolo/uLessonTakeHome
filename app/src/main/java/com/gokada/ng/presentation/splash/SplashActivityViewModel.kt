package com.gokada.ng.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gokada.core.base.BaseViewModel
import javax.inject.Inject

class SplashActivityViewModel @Inject constructor(
) : BaseViewModel() {

    private val _isUserLoggedInLiveData = MutableLiveData<Boolean>()
    val isUserLoggedInLiveData: LiveData<Boolean>
        get() = _isUserLoggedInLiveData

    private val _loggedInUserTypeLiveData = MutableLiveData<Int>()
    val loggedInUserTypeLiveData = _loggedInUserTypeLiveData as LiveData<Int>
}