package com.gokada.map.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.gokada.core.base.BaseActivity
import com.gokada.core.base.BaseViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

/**
 * Created by edetebenezer on 2019-08-10
 **/

abstract class BaseMapActivity<in D : ViewDataBinding, out V : BaseViewModel> :
    BaseActivity<D, V>(), OnMapReadyCallback {

    /**
     * This injection does nothing.
     * But for devices <21, D8 doesn't compile the APK due to duplicate BaseFragment_MembersInjector
     * classes.
     * *@Inject
     *lateinit var getLoggedInUserMapActivity: GetLoggedInUser
     * For more information, check;
     * https://github.com/google/dagger/issues/955 and
     * https://github.com/google/dagger/issues/1104
     */

    private var mMap: GoogleMap? = null

    abstract fun getMapId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpMap()
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            mMap = it
            startDemo()
        }
    }

    private fun setUpMap() {
        val mapFragment = supportFragmentManager.findFragmentById(getMapId()) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    protected abstract fun startDemo()

    protected fun getMap(): GoogleMap = mMap!!
}