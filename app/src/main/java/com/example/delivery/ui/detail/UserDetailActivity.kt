package com.example.delivery.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.delivery.R
import com.example.delivery.data.entity.UserLikeEntity
import com.example.delivery.databinding.ActivityUserDetailBinding
import com.example.delivery.ui.base.BaseActivity
import com.example.delivery.ui.detail.UserDetailViewModel.Companion.KEY_USERDATA
import com.example.delivery.utility.loadCenterCrop
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailActivity: BaseActivity<UserDetailViewModel, ActivityUserDetailBinding>() {

    private val userDetailData by lazy { intent.getParcelableExtra<UserLikeEntity>(KEY_USERDATA) }
    override val viewModel by viewModel<UserDetailViewModel> {
        parametersOf(userDetailData)
    }

    override fun getViewBinding() = ActivityUserDetailBinding.inflate(layoutInflater)

    override fun initActivity() {
        viewModel.fetchData()
        setClickListeners()
        observeViewModel()
    }

    private fun setClickListeners() = with(binding) {
        link.setOnClickListener {
            openWebView(userDetailData?.html_url ?: "")
        }

        likeImageButton.setOnClickListener {
            viewModel.setToggleLiked()
        }
    }

    private fun observeViewModel() = with(viewModel) {
        likeState.observe {
            if (it) binding.likeImageButton.setImageResource(R.drawable.ic_like_on)
            else binding.likeImageButton.setImageResource(R.drawable.ic_like_off)
        }
    }

    override fun observeData() {
        viewModel.userDetailStateLiveData.onUiState(
            error = { handleError() },
            success = { handleSuccess(it) }
        )
    }

    @SuppressLint("ShowToast")
    private fun handleError() {
        Toast.makeText(this , "네트워크 오류입니다.", Toast.LENGTH_SHORT )
    }

    private fun handleSuccess(data: UserLikeEntity) {
        binding.userImage.loadCenterCrop(data.avatar_url)
        binding.title.text = data.login
        binding.link.text = data.html_url
    }

    private fun openWebView(url: String) = startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))

}