package com.gokada.ng.auth.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gokada.core.base.BaseViewModel
import javax.inject.Inject

class OnBoardingFragmentViewModel @Inject constructor(
) : BaseViewModel() {

    private val _onBoardingStatus = MutableLiveData<Boolean>()
    val onBoardingStatus = _onBoardingStatus as LiveData<Boolean>

    private val _navigateToLoginFragment = MutableLiveData<Boolean>()
    val navigateToLoginFragment: LiveData<Boolean>
        get() = _navigateToLoginFragment

    fun gotToLoginFragment() {
        _navigateToLoginFragment.value = true
    }
}