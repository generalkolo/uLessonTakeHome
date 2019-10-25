package com.gokada.ng.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gokada.core.base.BaseActivity
import com.gokada.core.navigation.GokadaSuperAppIntentKey
import com.gokada.core.navigation.GokadaSuperAppIntentResolver
import com.gokada.core.navigation.GokadaSuperAppNavigator
import com.gokada.ng.BR
import com.gokada.ng.R
import com.gokada.ng.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var gokadaSuperAppNavigator: GokadaSuperAppNavigator

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = BR.ViewModel

    override fun getViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }

    override fun getBinding(binding: ActivityMainBinding) {
        this.binding = binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNavigationView.selectedItemId = R.id.feature_home
        initBottomNavBar()
    }

    private fun initBottomNavBar() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.feature_account -> {
                }
            }
            false
        }
    }

    companion object : GokadaSuperAppIntentResolver<GokadaSuperAppIntentKey.Main> {

        override fun getIntent(context: Context, key: GokadaSuperAppIntentKey.Main?): Intent {
            return Intent(context, MainActivity::class.java)
        }

    }
}