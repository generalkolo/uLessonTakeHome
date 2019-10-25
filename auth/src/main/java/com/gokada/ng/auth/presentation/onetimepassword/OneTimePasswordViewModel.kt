package com.gokada.ng.auth.presentation.onetimepassword

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.gokada.core.base.BaseViewModel
import com.gokada.core.utils.state.GokadaSuperAppResource
import com.gokada.remote.utils.ApiError
import io.reactivex.observers.DisposableObserver
import util.Utility
import javax.inject.Inject

class OneTimePasswordViewModel @Inject constructor(
) : BaseViewModel() {
    private val _loggedInUserTypeLiveData = MutableLiveData<Int>()
    val loggedInUserTypeLiveData: LiveData<Int>
        get() = _loggedInUserTypeLiveData

    private val _oneTimePasswordLiveData = MutableLiveData<GokadaSuperAppResource<Any>>()
    val oneTimePasswordLiveData = _oneTimePasswordLiveData as LiveData<GokadaSuperAppResource<Any>>

    private val _time = MutableLiveData<Long>()

    private val _showConfirmButton = MutableLiveData<Boolean>()
    val showConfirmButton: LiveData<Boolean>
        get() = _showConfirmButton

    private var phoneCode: String = "+234"
    //device related details
    var platformVersion: Int = 0
    var applicationVersion: Int = 0
    var deviceName: String? = null

    //timer logic
    private var timer: CountDownTimer? = null
    val timeString = Transformations.map(_time) { time ->
        DateUtils.formatElapsedTime(time)
    }

    lateinit var phoneNumber: String

    inner class ValidatePhoneNumberObserver : DisposableObserver<Int>() {
        override fun onComplete() {}

        override fun onNext(t: Int) {
            if (t > 0) {
                _oneTimePasswordLiveData.postValue(
                    GokadaSuperAppResource.success()
                )
                startTimerCountDown(t)
            } else _oneTimePasswordLiveData.postValue(
                GokadaSuperAppResource.failed(message = "Error - Please try again soon")
            )
        }

        override fun onError(e: Throwable) {
            _oneTimePasswordLiveData.postValue(
                GokadaSuperAppResource.failed(ApiError(e).message)
            )
        }
    }

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
    }

    init {
        _showConfirmButton.value = true
    }

    fun startTimerCountDown(seconds: Int) {
        //Initialize and set the timer codes
        timer?.let {
            timer?.cancel()
        }

        timer = object : CountDownTimer(
            (seconds * Utility.Companion.Constants.SECONDS_VALUE).toLong(), ONE_SECOND
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _time.value = millisUntilFinished / ONE_SECOND
                _showConfirmButton.postValue(true)
            }

            override fun onFinish() {
                _time.value = DONE
                _showConfirmButton.postValue(false)
            }
        }
        timer?.start()
    }

    fun setUserPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

    fun hashPhoneNumber(phoneNumber: String): String = phoneNumber.replaceRange(3, 7, "***")

    private fun concatPhoneCodeAndNumber(phoneCode: String, phoneNumber: String): String =
        "$phoneCode$phoneNumber"
}
