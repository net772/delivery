package com.example.delivery.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.delivery.R
import com.example.delivery.state.ResultState
import com.example.delivery.utility.config.ActivityConstants
import com.example.delivery.utility.extension.collect
import kotlinx.coroutines.flow.Flow

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    protected abstract fun createFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
    protected open fun initFragment() = Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createFragmentBinding(inflater, container).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }


    protected inline fun <T> LiveData<T>.observe(crossinline action: (T) -> Unit) {
        this.observe(viewLifecycleOwner) { action.invoke(it) }
    }

    protected inline fun <T> Flow<T>.onResult(crossinline action: (T) -> Unit) {
        collect(viewLifecycleOwner.lifecycleScope, action)
    }

    protected inline fun <T> Flow<ResultState<T>>.onUiState(
        crossinline loading: () -> Unit = {},
        crossinline success: (T) -> Unit = {},
        crossinline error: (Throwable) -> Unit = {},
        crossinline finish: () -> Unit = {}
    ) {
        onResult { state ->
            when (state) {
                ResultState.Loading -> loading()
                is ResultState.Success -> success(state.data)
                is ResultState.Error -> error(state.error)
                ResultState.Finish -> finish()
                else -> Unit
            }
        }
    }

    fun addFragment(
        @IdRes containerId: Int,
        fragment: Fragment?,
        fragmentManager: FragmentManager = childFragmentManager,
        action: String = "",
        addBackStack: Boolean = false
    ) {
        requireNotNull(fragment)

        val transaction = fragmentManager.beginTransaction()
        animFragment(transaction, action)
        transaction.add(containerId, fragment).apply {
            if(addBackStack) addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    fun replaceFragment(@IdRes containerId: Int, fragment: Fragment?, action : String = "", addBackStack : Boolean = false) {
        requireNotNull(fragment)

        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        animFragment(transaction, action)
        transaction.replace(containerId, fragment).apply {
            if(addBackStack) addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    fun removeFragment(
        fragment: Fragment?,
        fragmentManager: FragmentManager = childFragmentManager,
        action: String = "",
        popBackStack : Boolean = true
    ) {
        requireNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        animFragment(transaction, action)
        transaction.remove(fragment).commitAllowingStateLoss()
        if(popBackStack) fragmentManager.popBackStack()
    }

    private fun animFragment(transaction: FragmentTransaction, action: String) {
        when (action) {
            ActivityConstants.LEFT -> {
                transaction.setCustomAnimations(R.anim.left_in, R.anim.left_out)
            }
            ActivityConstants.RIGHT -> {
                transaction.setCustomAnimations(R.anim.right_in, R.anim.right_out)
            }
            ActivityConstants.UP -> {
                transaction.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out)
            }
            ActivityConstants.DOWN -> {
                transaction.setCustomAnimations(R.anim.push_down_in, R.anim.push_down_out)
            }
        }
    }
}