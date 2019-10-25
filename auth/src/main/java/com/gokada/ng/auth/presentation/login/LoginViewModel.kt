package com.gokada.ng.auth.presentation.login

import android.text.InputType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gokada.core.base.BaseViewModel
import com.gokada.core.utils.state.GokadaSuperAppResource
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    val numberInputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_NORMAL

    var phoneCode: String = "+234"

    var phoneNumber: String = ""

    private val _logUserInLiveData = MutableLiveData<GokadaSuperAppResource<Int>>()
    val logUserInLiveData = _logUserInLiveData as LiveData<GokadaSuperAppResource<Int>>

    private fun concatPhoneCodeAndNumber(phoneCode: String, phoneNumber: String): String =
        "$phoneCode$phoneNumber"
}