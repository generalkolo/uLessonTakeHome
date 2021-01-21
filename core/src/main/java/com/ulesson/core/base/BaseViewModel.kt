package com.ulesson.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulesson.core.utils.navigation.AppNavigationCommand
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

    private val _navigationCommand = MutableLiveData<AppNavigationCommand>()
    val navigationCommand = _navigationCommand as LiveData<AppNavigationCommand>

    fun navigate(command: AppNavigationCommand) {
        _navigationCommand.postValue(
            command
        )
    }
}
