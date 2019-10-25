package com.gokada.ng.presentation.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.gokada.core.base.BaseActivity
import com.gokada.core.navigation.GokadaSuperAppIntentKey
import com.gokada.core.navigation.GokadaSuperAppNavigator
import com.gokada.ng.BR
import com.gokada.ng.R
import com.gokada.ng.databinding.ActivitySplashBinding
import com.gokada.ng.utils.constants.GokadaUserConstants.ANIMATION_COMPLETED
import com.gokada.ng.utils.constants.GokadaUserConstants.USER_PILOT
import com.gokada.ng.utils.constants.GokadaUserConstants.USER_RIDER
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {

    @Inject
    lateinit var splashActivityViewModel: SplashActivityViewModel
    @Inject
    lateinit var gokadaSuperAppNavigator: GokadaSuperAppNavigator

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        binding.animationView.addAnimatorUpdateListener { animation ->
            val progress = (animation.animatedValue as Float * 100).toInt()
            if (progress == ANIMATION_COMPLETED) {
            }
        }
    }

    private fun setObservers() {
        splashActivityViewModel.isUserLoggedInLiveData.observe(this, Observer {
            when (it) {
                false -> startActivity(
                    gokadaSuperAppNavigator.createIntent(
                        this,
                        GokadaSuperAppIntentKey.Authentication()
                    )
                )
                else -> {}
            }
        })
        splashActivityViewModel.loggedInUserTypeLiveData.observe(this, Observer { userType ->
            when (userType) {
                USER_PILOT -> {
                }
                USER_RIDER -> {

                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel(): SplashActivityViewModel = splashActivityViewModel

    override fun getBindingVariable(): Int = BR.ViewModel

    override fun getBinding(binding: ActivitySplashBinding) {
        this.binding = binding
    }
}