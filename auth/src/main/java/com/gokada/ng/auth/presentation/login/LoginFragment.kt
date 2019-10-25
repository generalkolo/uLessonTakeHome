package com.gokada.ng.auth.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gokada.core.base.BaseFragment
import com.gokada.core.utils.navigation.GokadaNavigationCommand
import com.gokada.core.utils.state.GokadaSuperAppState
import com.gokada.ng.auth.BR
import com.gokada.ng.auth.R
import com.gokada.ng.auth.databinding.FragmentLoginBinding
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun getViewModel(): LoginViewModel = loginViewModel

    override fun getLayoutId() = R.layout.fragment_login

    private lateinit var binding: FragmentLoginBinding

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutBinding(binding: FragmentLoginBinding) {
        this.binding = binding
    }

    private fun setObservers() {
        loginViewModel.logUserInLiveData.observe(this, Observer {
            when (it.state) {
                GokadaSuperAppState.LOADING -> showLoadingDialog()
                GokadaSuperAppState.FAILED -> {
                    dismissLoadingDialog()
                    it.message?.let { message -> showSnackBar(binding.root, message, true) }
                }
                GokadaSuperAppState.SUCCESS -> {
                    dismissLoadingDialog()
                    val direction = LoginFragmentDirections.actionLoginFragmentToOneTimePasswordFragment(
                        loginViewModel.phoneNumber,
                        it.data!!
                    )
                    navigate(GokadaNavigationCommand.To(direction))
                }
                GokadaSuperAppState.VALIDATION_FAILED -> {
                    Toast.makeText(activity!!, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
    }
}
