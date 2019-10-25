package com.gokada.ng.auth.presentation.accountsetup

import android.text.InputType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gokada.core.base.BaseViewModel
import com.gokada.core.utils.state.GokadaSuperAppResource
import javax.inject.Inject

class AccountSetupViewModel @Inject constructor(
) : BaseViewModel() {
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    private var imageBase64: String = ""
    var imageName = ""

    val emailInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    //    val numberInputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_NORMAL
    val textInputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL

    var phoneNumber = MutableLiveData<String>()
    val zipCode = "+234"

    private val _setupUserAccountLiveData = MutableLiveData<GokadaSuperAppResource<Int>>()
    val setupUserAccountLiveData =
        _setupUserAccountLiveData as LiveData<GokadaSuperAppResource<Int>>

    private val _selectImage = MutableLiveData<Boolean>()
    val showImageSelector = _selectImage as LiveData<Boolean>

    private fun isEmailValid(emailAddress: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber.postValue(phoneNumber)
    }

    fun selectImage() {
        _selectImage.postValue(true)
    }

    fun imageSelectorShown() {
        _selectImage.postValue(null)
    }

    fun setBaseImageInBase64(base64Value: String, profileImageName: String) {
        this.imageBase64 = base64Value
        this.imageName = profileImageName
    }
}
