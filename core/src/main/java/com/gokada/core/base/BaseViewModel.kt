package com.gokada.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gokada.core.utils.navigation.GokadaNavigationCommand
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

    private val _navigationCommand = MutableLiveData<GokadaNavigationCommand>()
    val navigationCommand = _navigationCommand as LiveData<GokadaNavigationCommand>

    fun navigate(command: GokadaNavigationCommand) {
        _navigationCommand.postValue(
            command
        )
    }
}