package com.gokada.ng.auth.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gokada.core.base.BaseFragment
import com.gokada.core.utils.navigation.GokadaNavigationCommand
import com.gokada.ng.auth.BR
import com.gokada.ng.auth.R
import com.gokada.ng.auth.databinding.FragmentOnboardingBinding
import util.Utility.Companion.Constants.ANIMATION_COMPLETED
import javax.inject.Inject

class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding, OnBoardingFragmentViewModel>() {

    @Inject
    lateinit var onBoardingFragmentViewModel: OnBoardingFragmentViewModel

    override fun getViewModel(): OnBoardingFragmentViewModel = onBoardingFragmentViewModel

    override fun getLayoutId(): Int = R.layout.fragment_onboarding

    override fun getBindingVariable(): Int = BR.viewModel

    private lateinit var binding: FragmentOnboardingBinding

    override fun getLayoutBinding(binding: FragmentOnboardingBinding) {
        this.binding = binding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onBoardingFragmentViewModel.onBoardingStatus.observe(this, Observer {
            it?.let {
                if (it) {
                    navigate(
                        GokadaNavigationCommand.To(
                            OnBoardingFragmentDirections.actionFragmentOnBoardingToLoginFragment()
                        )
                    )
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnGetStarted.setOnClickListener {
            binding.animationView.playAnimation()
        }
    }

    private fun setObservers() {
        onBoardingFragmentViewModel.navigateToLoginFragment.observe(this, Observer { toNavigate ->
            toNavigate?.let {
                navigate(
                    GokadaNavigationCommand.To(
                        OnBoardingFragmentDirections.actionFragmentOnBoardingToLoginFragment()
                    )
                )
            }
        })
        binding.animationView.addAnimatorUpdateListener { animation ->
            val progress = (animation.animatedValue as Float * 100).toInt()
            if (progress == ANIMATION_COMPLETED){
                onBoardingFragmentViewModel.gotToLoginFragment()
            }
        }
    }
}