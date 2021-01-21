package com.ulesson.takehome.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ulesson.core.adapters.SingleLayoutRecyclerAdapter
import com.ulesson.core.base.BaseFragment
import com.ulesson.core.listeners.BindableItemClickListener
import com.ulesson.core.utils.navigation.AppNavigationCommand
import com.ulesson.core.utils.state.AppState
import com.ulesson.domain.models.SubjectsItem
import com.ulesson.domain.models.SubjectsModel
import com.ulesson.takehome.BR
import com.ulesson.takehome.R
import com.ulesson.takehome.databinding.DashboardFragmentBinding
import com.ulesson.takehome.databinding.SubjectsItemBinding
import com.ulesson.takehome.presentation.MainActivity
import com.ulesson.takehome.presentation.MainActivityViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class DashboardFragment : BaseFragment<DashboardFragmentBinding, MainActivityViewModel>(),
    BindableItemClickListener<SubjectsItem> {

    private val getMainViewModel by lazy {
        (requireActivity() as MainActivity).mainActivityViewModel
    }

    private val subjectsAdapter by lazy {
        object :
            SingleLayoutRecyclerAdapter<SubjectsItemBinding, SubjectsItem>(
                context = context!!,
                itemClickListener = this@DashboardFragment,
                layoutId = R.layout.subjects_item
            ) {
            override fun getItemBindingVariable(): Int = BR.subject

            override fun getItemClickListenerBindingVariable(): Int = BR.Listener
        }
    }

    private lateinit var binding: DashboardFragmentBinding

    override fun getViewModel(): MainActivityViewModel = getMainViewModel

    override fun getLayoutId(): Int = R.layout.dashboard_fragment

    override fun getBindingVariable(): Int = 0

    override fun getLayoutBinding(binding: DashboardFragmentBinding) {
        this.binding = binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setObservers()
    }

    private fun setObservers() {
        with(getMainViewModel) {
            subjectsInformation.observe(this@DashboardFragment, Observer {
                when (it.state) {
                    AppState.LOADING -> showLoadingDialog()
                    AppState.FAILED, AppState.VALIDATION_FAILED -> {
                        dismissLoadingDialog()
                        it.message?.let { message -> showSnackBar(binding.root, message, true) }
                    }
                    AppState.SUCCESS -> {
                        dismissLoadingDialog()
                        loadData(it.data)
                    }
                }
            })
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            subjectsRecView.apply {
                adapter = subjectsAdapter
                itemAnimator = SlideInUpAnimator().apply {
                    addDuration = 500
                    removeDuration = 500
                    changeDuration = 500
                    moveDuration = 500
                }
            }
        }
    }

    private fun loadData(data: SubjectsModel?) {
        data?.let {
            subjectsAdapter.items = it.subjects
        }
    }

    override fun onItemClicked(item: SubjectsItem) {
        getMainViewModel.setClickedSubject(item)
        navigate(
            AppNavigationCommand.ToDestination(
                DashboardFragmentDirections.actionDashboardFragmentToSubjectsFragment(
                    item.name
                )
            )
        )
    }
}
