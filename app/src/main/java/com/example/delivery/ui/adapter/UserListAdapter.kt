package com.example.delivery.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery.R
import com.example.delivery.data.entity.UserLikeEntity
import com.example.delivery.databinding.ItemUsersBinding
import com.example.delivery.utility.loadCenterCrop

class UserListAdapter(
    private val callback: (data: UserLikeEntity) -> Unit
) : CustomRecyclerViewAdapter() {

    private val adapterList = arrayListOf<UserLikeEntity>()

    override fun <T> replaceData(list: List<T>?) {
        list?.let {
            adapterList.clear()
            adapterList.addAll(list as ArrayList<UserLikeEntity>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserItemViewHolder).bindData(adapterList[position])
    }

    override fun getItemCount() = adapterList.size


    inner class UserItemViewHolder(
        private val binding: ItemUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: UserLikeEntity) = with(binding) {
            userImage.loadCenterCrop(data.avatar_url)
            title.text = data.login
            content.text = data.html_url

            if (data.state) likeImageButton.setImageResource(R.drawable.ic_like_on)
            else likeImageButton.setImageResource(R.drawable.ic_like_off)

            root.setOnClickListener {
                callback.invoke(data)
            }
        }
    }
}