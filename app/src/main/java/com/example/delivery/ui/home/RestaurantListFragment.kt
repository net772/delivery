package com.example.delivery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.delivery.data.entity.location.LocationLatLngEntity
import com.example.delivery.databinding.FragmentListBinding
import com.example.delivery.ui.adapter.RestaurantListAdapter
import com.example.delivery.ui.base.BaseFragment
import com.example.delivery.ui.home.restaurant.RestaurantCategory
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantListFragment: BaseFragment<FragmentListBinding>() {
    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory } // 이건 좀 더 자료 찾아보고 바꿀 수 있으면 바꾸자!
    private val locationLatLngEntity by lazy<LocationLatLngEntity> { arguments?.getParcelable(LOCATION_KEY)!! }


    private val viewModel: RestaurantListViewModel by viewModel()

    private val restaurantListAdapter by lazy {
        RestaurantListAdapter(requireContext()) {

        }
    }

    override fun createFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentListBinding.inflate(inflater, container, false)

    override fun initFragment() {
        initAdapter()
    }

    private fun initAdapter() = with(binding) {
        recyclerVIew.apply {
            setHasFixedSize(true)
            adapter = restaurantListAdapter
        }
    }

    companion object {
        const val RESTAURANT_KEY = "Restaurant"
        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"
        const val LOCATION_KEY = "location"

        fun newInstance(restaurantCategory: RestaurantCategory): RestaurantListFragment {
            return RestaurantListFragment().apply {
                arguments = bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory // ex) -> (restaurantCategory, ALL)
                )
            }
        }
    }

}