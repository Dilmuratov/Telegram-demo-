package com.example.telegramdemo.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.databinding.FragmentChatBinding
import com.example.telegramdemo.presentation.contactviewmodel.ContactsViewModel
import com.example.telegramdemo.presentation.groupviewmodel.GroupsViewModel
import com.example.telegramdemo.presentation.messageviewmodel.MessageViewModel
import com.example.telegramdemo.ui.adapter.MessageAdapter
import com.example.telegramdemo.utils.toTimeFormat
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment(R.layout.fragment_chat) {
    private lateinit var binding: FragmentChatBinding
    private val messageViewModel: MessageViewModel by viewModel()
    private val groupsViewModel: GroupsViewModel by viewModel()
    private lateinit var adapter: MessageAdapter
    private lateinit var pref: SharedPreferences
    private lateinit var userId: String
    private lateinit var username: String
    private lateinit var groupPath: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        getUserId()

        initObservers()

        sendMessage()

        refreshRecyclerView()

        adapter.currentList.forEach {
            Log.d(
                "TTTT",
                "Message: ${it.userId}\nBy: ${it.message}\nTime: ${it.time}\nID: ${it.messageId}"
            )
        }
    }

    private fun getUserId() {
        pref = (requireContext()).getSharedPreferences("pref", Context.MODE_PRIVATE)
        userId = pref.getString("userId", "").toString()
        username = pref.getString("username", "").toString()
        Log.d("TTTT", "UserId(ChatFragment): $userId\nUsername(ChatFragent): $username")
    }

    private fun initObservers() {
        groupPath = arguments?.getString("groupPath", "defaultValue1") ?: "defaultValue2"
        Log.d("TTTT", "GroupPath(ChatFragment): $groupPath")

        adapter = MessageAdapter(pref)

        binding.recyclerView.adapter = adapter


        lifecycleScope.launch {
            messageViewModel.getAllMessagesLiveData.observe(requireActivity()) {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            messageViewModel.getAllMessages(groupPath)
        }
    }

    private fun sendMessage() {
        binding.ivSend.setOnClickListener {
            val message = binding.editText.text.toString()
            if (message.isNotEmpty()) {
                val currentTimeMillis = System.currentTimeMillis()
                val messageData = MessageData(
                    messageId = currentTimeMillis.toString(),
                    message = message,
                    time = toTimeFormat(currentTimeMillis),
                    userId = userId,
                    username = username
                )
                val result = lifecycleScope.launch {
                    messageViewModel.addMessage(messageData, groupPath)
                }
                if (result.isCompleted) {
                    binding.editText.setText("")

                    lifecycleScope.launch {
                        messageViewModel.getAllMessages(groupPath)
                        groupsViewModel.updateGroup(
                            ChatData(
                                groupId = arguments?.getString("groupId", "").toString(),
                                groupName = arguments?.getString("groupName", "").toString(),
                                groupPath = groupPath,
                                lastSms = message
                            )
                        )
                    }
                }
            }
        }
    }

    private fun refreshRecyclerView() {

        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.swipeRefreshLayout.isRefreshing = true

            lifecycleScope.launch {
                messageViewModel.getAllMessages(groupPath)
            }

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}