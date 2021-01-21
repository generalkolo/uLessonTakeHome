package com.ulesson.core.base

import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ulesson.core.utils.navigation.AppNavigationCommand
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment<in D : ViewDataBinding, out V : BaseViewModel> : DaggerFragment() {

    /**
     * There should be an injection done here
     * example -
     * *@Inject
     * lateinit var getLoggedInUser: GetLoggedInUser
     * For more information, check;
     * https://github.com/google/dagger/issues/955 and
     * https://github.com/google/dagger/issues/1104
     */

    abstract fun getViewModel(): V

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getLayoutBinding(binding: D)

    open fun setViewModelObservers() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: D = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.apply {
            setVariable(getBindingVariable(), getViewModel())
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }
        setViewModelObservers()
        getLayoutBinding(binding)
        return binding.root
    }

    protected fun navigate(navCommand: AppNavigationCommand) {
        when (navCommand) {
            is AppNavigationCommand.Back -> findNavController().navigateUp()
            is AppNavigationCommand.ToDestination -> findNavController().navigate(navCommand.direction)
            //is GokadaNavigationCommand.BackToRoot -> findNavController().popBackStack()
        }
    }

    open fun setBackPressedListener(actionToPerform: () -> Unit) {
        var backPressedCallback : OnBackPressedCallback? = null
        val performActionAndRemoveCallback =  {
            actionToPerform()
            backPressedCallback!!.remove()
        }
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                performActionAndRemoveCallback()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)
    }

    fun hideKeyBoard(token: IBinder) {
        val inputMethodManager = activity?.getSystemService<InputMethodManager>()
        inputMethodManager?.hideSoftInputFromWindow(token, 0)
    }

    fun showKeyBoard() {
        val inputMethodManager = activity?.getSystemService<InputMethodManager>()
        inputMethodManager?.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    fun showSnackBar(
        view: View,
        message: String,
        isError: Boolean = false,
        duration: Int = Snackbar.LENGTH_SHORT,
        isWarning: Boolean = false
    ) =
        (activity as BaseActivity<*, *>).showSnackBar(view, message, isError, duration, isWarning)

    fun showLoadingDialog() = (activity as BaseActivity<*, *>).showLoadingDialog()

    fun dismissLoadingDialog() = (activity as BaseActivity<*, *>).dismissLoadingDialog()

    fun isNetworkConnected(): Boolean = (activity as BaseActivity<*, *>).isNetworkConnected()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel().navigationCommand.observe(this, Observer { navCommand ->
            when (navCommand) {
                is AppNavigationCommand.Back -> findNavController().navigateUp()
                is AppNavigationCommand.ToDestination -> findNavController().navigate(navCommand.direction)
            }
        })
    }
}
