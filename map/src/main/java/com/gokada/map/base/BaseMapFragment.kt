package com.gokada.map.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gokada.core.base.BaseFragment
import com.gokada.core.base.BaseViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

/**
 * Created by edetebenezer on 2019-08-10
 **/

abstract class BaseMapFragment<in D : ViewDataBinding, out V : BaseViewModel> :
    BaseFragment<D, V>(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null

    abstract fun getMapId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: D = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.apply {
            setVariable(getBindingVariable(), getViewModel())
            executePendingBindings()
            lifecycleOwner = this@BaseMapFragment
        }
        setUpMap()
        getLayoutBinding(binding)
        return binding.root
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            mMap = it
            startDemo()
        }
    }

    private fun setUpMap() {
        val mapFragment = childFragmentManager.findFragmentById(getMapId()) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    protected abstract fun startDemo()

    protected fun getMap(): GoogleMap = mMap!!
}