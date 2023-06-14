package com.example.telegramdemo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.UserData
import com.example.telegramdemo.databinding.ItemChatBinding

class ContactAdapter : ListAdapter<UserData, ContactAdapter.UserViewHolder>(
    object : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData) = oldItem == newItem

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData) =
            oldItem.userId == newItem.userId &&
                    oldItem.phoneNumber == newItem.phoneNumber &&
                    oldItem.userName == newItem.userName &&
                    oldItem.userSurname == newItem.userSurname
    }
) {

    inner class UserViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val userData = getItem(position)
            binding.tvGroupName.text = if (userData.userSurname.isNullOrEmpty().not()) {
                "${userData.userName} ${userData.userSurname}"
            } else {
                userData.userName
            }
            binding.tvLastSms.text = userData.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        val binding = ItemChatBinding.bind(view)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }
}