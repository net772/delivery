package com.example.delivery.ui.home

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.delivery.databinding.FragmentHomeBinding
import com.example.delivery.ui.adapter.RestaurantListFragmentPagerAdapter
import com.example.delivery.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var viewPagerAdapter: RestaurantListFragmentPagerAdapter

    private lateinit var locationManager: LocationManager
    private lateinit var myLocationListener: MyLocationListener

    override fun createFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)


    override fun initFragment() {

    }


    /** 위치 변환 콜백 **/
    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            TODO("Not yet implemented")
        }

    }
}