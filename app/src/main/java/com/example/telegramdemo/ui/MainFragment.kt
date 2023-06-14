package com.example.telegramdemo.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.databinding.FragmentMainBinding
import com.example.telegramdemo.presentation.groupviewmodel.GroupsViewModel
import com.example.telegramdemo.ui.adapter.ChatAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding
    private val groupsViewModel: GroupsViewModel by viewModel()
    private lateinit var pref: SharedPreferences
    private var adapter = ChatAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        checkUserIdOrCreateUserId()

        initObservers()

        initListeners()

        refreshRecyclerView()

    }

    private fun checkUserIdOrCreateUserId() {
        pref = (requireContext()).getSharedPreferences("pref", Context.MODE_PRIVATE)
        var userId = pref.getString("userId", "")
        var username = pref.getString("username", "")
        if (userId == "") {
            val currentTimeMillis = System.currentTimeMillis().toString()
            pref.edit().putString("userId", currentTimeMillis).apply()
            pref.edit().putString("username", currentTimeMillis).apply()
            userId = pref.getString("userId", "")
            username = pref.getString("username", "")
        }
        binding.tvUsername.text = username
        Log.d("TTTT", "UserId:\t" + userId.toString() + "\nUsername:\t" + username.toString())
    }

    private fun initObservers() {
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            groupsViewModel.getAllGroupsLiveData.observe(requireActivity()) {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            groupsViewModel.getAllGroups()
        }

        if (adapter.currentList.isEmpty()) {
            Toast.makeText(requireContext(), "List is Empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners() {
        adapter.setOnItemClickListener { chatData ->
            val result = lifecycleScope.launch {
                groupsViewModel.updateGroup(
                    ChatData(
                        groupId = chatData.groupId,
                        groupPath = chatData.groupPath,
                        groupName = chatData.groupName,
                        lastSms = chatData.lastSms
                    )
                )
            }
            if (result.isCompleted) {
                val bundle = Bundle()
                bundle.putString("groupId", chatData.groupId)
                bundle.putString("groupPath", chatData.groupPath)
                bundle.putString("groupName", chatData.groupName)
                findNavController().navigate(R.id.action_mainFragment_to_chatFragment, bundle)
            } else {
                Toast.makeText(requireContext(), "Please try again later", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.ivMenu.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)
        }

        binding.tvAddGroup.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addGroupFragment)
        }

        binding.tvContacts.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_contactsFragment)
        }
    }

    private fun refreshRecyclerView() {

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true

            lifecycleScope.launch {
                groupsViewModel.getAllGroups()
            }

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}