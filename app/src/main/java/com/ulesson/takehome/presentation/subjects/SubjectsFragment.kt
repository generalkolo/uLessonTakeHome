package com.ulesson.takehome.presentation.subjects

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ulesson.core.base.BaseFragment
import com.ulesson.core.utils.EventObserver
import com.ulesson.core.utils.navigation.AppNavigationCommand
import com.ulesson.domain.models.ChaptersItem
import com.ulesson.takehome.BR
import com.ulesson.takehome.R
import com.ulesson.takehome.databinding.ChaptersItemBinding
import com.ulesson.takehome.databinding.SubjectsFragmentBinding
import com.ulesson.takehome.presentation.MainActivity
import com.ulesson.takehome.presentation.MainActivityViewModel
import com.ulesson.takehome.utils.SingleLayoutSubjectsRecAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.chapters_item.view.*

class SubjectsFragment : BaseFragment<SubjectsFragmentBinding, MainActivityViewModel>() {

    private val getMainViewModel by lazy {
        (requireActivity() as MainActivity).mainActivityViewModel
    }

    private val args: SubjectsFragmentArgs by navArgs()

    private lateinit var binding: SubjectsFragmentBinding

    override fun getViewModel(): MainActivityViewModel = getMainViewModel

    override fun getLayoutId(): Int = R.layout.subjects_fragment

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutBinding(binding: SubjectsFragmentBinding) {
        this.binding = binding
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getMainViewModel.navigateToVideoFragment.observe(this@SubjectsFragment, EventObserver {
            if (it) {
                navigate(AppNavigationCommand.ToDestination(SubjectsFragmentDirections.actionSubjectsFragmentToVideoFragment()))
            }
        })
    }

    private val chaptersDataAdapter by lazy {
        object :
            SingleLayoutSubjectsRecAdapter<ChaptersItemBinding, ChaptersItem>(
                context = context!!,
                itemClickListener = null,
                layoutId = R.layout.chapters_item,
                viewModel = getMainViewModel
            ) {
            override fun getItemBindingVariable(): Int = BR.chapter

            override fun getItemClickListenerBindingVariable(): Int = 0

            override fun getMainViewModelBindingVariable(): Int = BR.innerViewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setObservers()
        setupToolbar()
    }

    private fun setupToolbar() {
        //Setup toolbar text (Clicked subject name)
        args.let {
            if (it.subjectName.isNotEmpty())
                binding.subjectName.text = it.subjectName
        }
        //Setup click listener
        binding.backArrow.setOnClickListener {
            navigate(AppNavigationCommand.Back)
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            subjectInfoRecView.apply {
                adapter = chaptersDataAdapter
                itemAnimator = SlideInUpAnimator().apply {
                    addDuration = 500
                    removeDuration = 500
                    changeDuration = 500
                    moveDuration = 500
                }
            }
        }
    }

    private fun setObservers() {
        with(getMainViewModel) {
            subjectsItem.observe(this@SubjectsFragment, Observer {
                it?.let {
                    chaptersDataAdapter.items = it.chapters
                }
            })
        }
    }
}
