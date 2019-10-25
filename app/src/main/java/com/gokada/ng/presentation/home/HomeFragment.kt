package com.gokada.ng.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gokada.core.adapters.SingleLayoutRecyclerAdapter
import com.gokada.core.base.BaseFragment
import com.gokada.core.listeners.BindableItemClickListener
import com.gokada.core.navigation.GokadaSuperAppNavigator
import com.gokada.ng.BR
import com.gokada.ng.R
import com.gokada.ng.databinding.FragmentHomeBinding
import com.gokada.ng.databinding.LayoutServicesItemBinding
import com.gokada.ng.models.ServiceModel
import com.gokada.ng.utils.constants.GokadaUserConstants.USER_PILOT
import com.gokada.ng.utils.constants.GokadaUserConstants.USER_RIDER
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(),
    BindableItemClickListener<ServiceModel> {

    @Inject
    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    @Inject
    lateinit var gokadaSuperAppNavigator: GokadaSuperAppNavigator

    private val servicesAdapter by lazy {
        object : SingleLayoutRecyclerAdapter<LayoutServicesItemBinding, ServiceModel>(
            context = context!!,
            itemClickListener = this@HomeFragment,
            layoutId = R.layout.layout_services_item
        ) {
            override fun getItemBindingVariable(): Int = BR.Service

            override fun getItemClickListenerBindingVariable(): Int = BR.ClickListener
        }
    }

    private lateinit var binding: FragmentHomeBinding

    override fun getViewModel(): HomeFragmentViewModel = homeFragmentViewModel

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getBindingVariable(): Int = 0

    override fun getLayoutBinding(binding: FragmentHomeBinding) {
        this.binding = binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObserver()
        binding.services.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            itemAnimator = ScaleInAnimator().apply {
                addDuration = 1000
                removeDuration = 1000
                moveDuration = 1000
                changeDuration = 1000
            }
            adapter = servicesAdapter
            OverScrollDecoratorHelper.setUpOverScroll(
                this,
                OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
            )
        }
    }

    private fun setObserver() {
        homeFragmentViewModel.loggedInUserLiveData.observe(this, Observer {
            it.let {
                when (it) {
                    USER_PILOT -> {
                        servicesAdapter.items = listOf(
                            ServiceModel(
                                name = "G Pilot",
                                iconResource = R.drawable.ic_gokada_pilot
                            )
                        )
                    }
                    USER_RIDER -> {
                        servicesAdapter.items = listOf(
                            ServiceModel(
                                name = "G Okada",
                                iconResource = R.drawable.ic_gokada_rider
                            )
                        )
                    }
                }
                servicesAdapter.addNewItems(
                    listOf(
                        ServiceModel(name = "G Wallet", iconResource = R.drawable.ic_gokada_wallet),
                        ServiceModel(name = "G News", iconResource = R.drawable.ic_gokada_news)
                    )
                )
            }
        })
    }

    override fun onItemClicked(item: ServiceModel) {
    }
}