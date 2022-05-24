package com.example.delivery.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.delivery.R
import com.example.delivery.databinding.ActivityMainBinding
import com.example.delivery.ui.base.BaseActivity
import com.example.delivery.ui.home.HomeFragment
import com.example.delivery.ui.like.RestaurantLikeListFragment
import com.example.delivery.ui.my.MyFragment
import com.example.delivery.utility.event.BottomNavigatorEvent
import com.example.delivery.utility.event.EventBus
import com.example.delivery.utility.extension.onEvent
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener {
    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initActivity() {
        initDataBinding()
        collectEvent()
        EventBus.emit(BottomNavigatorEvent.Home(R.id.menu_home))
    }

    private fun collectEvent() {
        onEvent<BottomNavigatorEvent> { goToTab(it) }
    }

    fun goToTab(mainTabMenu: BottomNavigatorEvent) {
        binding.bottomNav.selectedItemId = mainTabMenu.menuId
    }

    private fun initDataBinding() {
        binding.userViewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }

            R.id.menu_like -> {
                showFragment(RestaurantLikeListFragment.newInstance(), RestaurantLikeListFragment.TAG)
                true
            }

            R.id.menu_my -> {
                showFragment(MyFragment.newInstance(), MyFragment.TAG)
                true
            }
            else -> false
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

}