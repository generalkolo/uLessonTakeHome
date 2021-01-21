package com.ulesson.takehome.presentation

import com.ulesson.core.base.BaseActivity
import com.ulesson.takehome.BR
import com.ulesson.takehome.R
import com.ulesson.takehome.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = BR.ViewModel

    override fun getViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }

    override fun getBinding(binding: ActivityMainBinding) {
        this.binding = binding
    }
}
