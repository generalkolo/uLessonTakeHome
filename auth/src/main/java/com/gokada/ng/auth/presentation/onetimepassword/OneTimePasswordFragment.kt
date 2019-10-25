package com.gokada.ng.auth.presentation.onetimepassword

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gokada.core.base.BaseFragment
import com.gokada.core.navigation.GokadaSuperAppNavigator
import com.gokada.core.utils.navigation.GokadaNavigationCommand
import com.gokada.core.utils.state.GokadaSuperAppState
import com.gokada.ng.auth.BR
import com.gokada.ng.auth.BuildConfig
import com.gokada.ng.auth.R
import com.gokada.ng.auth.databinding.FragmentOneTimePasswordBinding
import com.jaredrummler.android.device.DeviceName
import org.jetbrains.anko.toast
import util.Utility.Companion.Constants.USER_PILOT
import util.Utility.Companion.Constants.USER_RIDER
import javax.inject.Inject

class OneTimePasswordFragment :
    BaseFragment<FragmentOneTimePasswordBinding, OneTimePasswordViewModel>() {

    @Inject
    lateinit var gokadaSuperAppNavigator: GokadaSuperAppNavigator
    private lateinit var binding: FragmentOneTimePasswordBinding

    override fun getLayoutBinding(binding: FragmentOneTimePasswordBinding) {
        this.binding = binding
    }

    @Inject
    lateinit var oneTimePasswordViewModel: OneTimePasswordViewModel

    override fun getViewModel() = oneTimePasswordViewModel

    override fun getLayoutId() = R.layout.fragment_one_time_password

    override fun getBindingVariable() = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getArgumentsAndPerformActions()
        setObservers()
        setDeviceInformation()
    }

    override fun setBackPressedListener(actionToPerform: () -> Unit) {
        //Override the back pressed listener to do nothing
    }

    private fun setDeviceInformation() {
        oneTimePasswordViewModel.platformVersion = android.os.Build.VERSION.SDK_INT
        oneTimePasswordViewModel.applicationVersion = BuildConfig.VERSION_CODE
        oneTimePasswordViewModel.deviceName = DeviceName.getDeviceName()
    }

    private fun getArgumentsAndPerformActions() {
        val arguments = OneTimePasswordFragmentArgs.fromBundle(arguments!!)
        arguments.apply {
            binding.tvHelperText.text =
                getString(
                    R.string.otp_confirm_formatter,
                    oneTimePasswordViewModel.hashPhoneNumber(phoneNumber)
                )
            oneTimePasswordViewModel.startTimerCountDown(timeToLive)
            oneTimePasswordViewModel.setUserPhoneNumber(phoneNumber)
        }
    }

    private fun setObservers() {
        oneTimePasswordViewModel.oneTimePasswordLiveData.observe(this, Observer {
            when (it.state) {
                GokadaSuperAppState.FAILED -> {
                    dismissLoadingDialog()
                    it.message?.let { message -> showSnackBar(binding.root, message, true) }
                }
                GokadaSuperAppState.LOADING ->
                    showLoadingDialog()
                GokadaSuperAppState.SUCCESS -> {
                    dismissLoadingDialog()
                    determineLandingPage(it.data)
                }
                GokadaSuperAppState.VALIDATION_FAILED -> {
                    activity?.toast(it.message.toString())
                    dismissLoadingDialog()
                }
            }
        })

        oneTimePasswordViewModel.loggedInUserTypeLiveData.observe(this, Observer {
            when (it) {
                USER_PILOT -> goToCorrectModule(USER_PILOT)
            }
        })
    }

    private fun saveOnBoardingStatusAndToken() {
    }

    private fun determineLandingPage(data: Any?) {
        data?.let {
            if (data as Boolean) goToCorrectModule(USER_RIDER)
            if (!data) {
                navigate(
                    GokadaNavigationCommand.To(
                        OneTimePasswordFragmentDirections.actionOneTimePasswordFragmentToAccountSetupFragment(
                            oneTimePasswordViewModel.phoneNumber
                        )
                    )
                )
            }
        }
    }

    private fun goToCorrectModule(userType: Int) {
        saveOnBoardingStatusAndToken()
        activity?.finishAffinity()
        when (userType) {
            USER_PILOT -> {
            }
            USER_RIDER -> {
            }
        }
    }
}