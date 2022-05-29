package com.example.delivery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.delivery.R
import com.example.delivery.data.entity.RestaurantModel
import com.example.delivery.databinding.ItemRestaurantBinding
import com.example.delivery.ui.base.BaseDiffUtilAdapter
import com.example.delivery.utility.extension.loadCenterCrop

class RestaurantListAdapter(
    private val context: Context,
    private val onClick : () -> Unit
) : BaseDiffUtilAdapter<ItemRestaurantBinding, RestaurantModel>() {

    override fun areItemsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean =
        oldItem.restaurantInfoId == newItem.restaurantInfoId

    override fun getBinding(
        parent: ViewGroup,
        viewType: Int,
    ) = ViewHolder(ItemRestaurantBinding.inflate(LayoutInflater.from(context), parent, false))

    inner class ViewHolder(
        override val binding : ItemRestaurantBinding
    ) : BaseViewHolder<ItemRestaurantBinding>(binding) {
        override fun bind(position: Int) = with(binding) {
            val data = adapterList[position]

            restaurantImage.loadCenterCrop(data.restaurantImageUrl, 24f)
            restaurantTitleText.text = data.restaurantTitle
            gradeText.text = context.getString(R.string.grade_format, data.grade)
            reviewCountText.text = context.getString(R.string.review_count, data.reviewCount)
            val (minTime, maxTime) = data.deliveryTimeRange
            deliveryTimeText.text = context.getString(R.string.delivery_time, minTime, maxTime)

            val (minTip, maxTip) = data.deliveryTipRange
            deliveryTipText.text = context.getString(R.string.delivery_tip, minTip, maxTip)
        }
    }
}