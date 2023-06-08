package com.example.telegramdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.databinding.ItemChatBinding

class ChatAdapter : ListAdapter<ChatData, ChatAdapter.ChatViewHolder>(
    object : DiffUtil.ItemCallback<ChatData>() {
        override fun areItemsTheSame(oldItem: ChatData, newItem: ChatData) = oldItem == newItem

        override fun areContentsTheSame(oldItem: ChatData, newItem: ChatData) =
            oldItem.groupPath == newItem.groupPath &&
                    oldItem.groupName == newItem.groupName &&
                    oldItem.lastSms == newItem.lastSms &&
                    oldItem.groupId == newItem.groupId
    }
) {
    inner class ChatViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val chatData = getItem(position)
            binding.tvGroupName.text = chatData.groupName
            binding.tvLastSms.text = chatData.lastSms

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(chatData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder =
        ChatViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(position)
    }

    private var onItemClickListener: ((ChatData) -> Unit)? = null
    fun setOnItemClickListener(block: ((ChatData) -> Unit)) {
        onItemClickListener = block
    }
}