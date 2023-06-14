package com.example.telegramdemo.ui.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.databinding.ItemMessageOtherBinding
import com.example.telegramdemo.databinding.ItemMessageUserBinding
import com.example.telegramdemo.presentation.contactviewmodel.ContactsViewModel

class MessageAdapter(
    private val preferences: SharedPreferences,
) :
    ListAdapter<MessageData, ViewHolder>(object : DiffUtil.ItemCallback<MessageData>() {
        override fun areItemsTheSame(oldItem: MessageData, newItem: MessageData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MessageData, newItem: MessageData) =
            oldItem.messageId == newItem.messageId &&
                    oldItem.userId == newItem.userId &&
                    oldItem.time == newItem.time &&
                    oldItem.message == newItem.message &&
                    oldItem.username == newItem.username
    }) {

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].userId == preferences.getString("userId", "")
        ) 1
        else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_user, parent, false)
                val binding = ItemMessageUserBinding.bind(view)
                UserMessageViewHolder(binding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_other, parent, false)
                val binding = ItemMessageOtherBinding.bind(view)
                OtherMessageViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> (holder as UserMessageViewHolder).bind(position)
            2 -> (holder as OtherMessageViewHolder).bind(position)
        }
    }

    inner class UserMessageViewHolder(private val binding: ItemMessageUserBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            val messageData = getItem(position)
            binding.tvMessage.text = messageData.message
            binding.tvTime.text = messageData.time
        }
    }

    inner class OtherMessageViewHolder(private val binding: ItemMessageOtherBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            val messageData = getItem(position)
            binding.tvMessage.text = messageData.message
            binding.tvTime.text = messageData.time
            binding.tvUsername.text = messageData.username
        }
    }
}