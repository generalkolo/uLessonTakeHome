package com.gokada.ng.auth.presentation

import android.content.Context
import android.content.Intent
import com.gokada.core.base.BaseActivity
import com.gokada.core.navigation.GokadaSuperAppIntentKey
import com.gokada.core.navigation.GokadaSuperAppIntentResolver
import com.gokada.ng.auth.R
import com.gokada.ng.auth.databinding.ActivityAuthenticationBinding
import javax.inject.Inject

class AuthenticationActivity :
    BaseActivity<ActivityAuthenticationBinding, AuthenticationActivityViewModel>() {

    private lateinit var binding: ActivityAuthenticationBinding

    @Inject
    lateinit var authenticationActivityViewModel: AuthenticationActivityViewModel

    override fun getLayoutId(): Int = R.layout.activity_authentication

    override fun getBindingVariable(): Int = 0

    override fun getViewModel(): AuthenticationActivityViewModel = authenticationActivityViewModel

    override fun getBinding(binding: ActivityAuthenticationBinding) {
        this.binding = binding
    }

    companion object : GokadaSuperAppIntentResolver<GokadaSuperAppIntentKey.Authentication> {
        override fun getIntent(
            context: Context,
            key: GokadaSuperAppIntentKey.Authentication?
        ): Intent {
            return Intent(context, AuthenticationActivity::class.java)
        }
    }
}