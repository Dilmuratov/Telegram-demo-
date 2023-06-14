package com.example.telegramdemo.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.databinding.FragmentAddGroupBinding
import com.example.telegramdemo.presentation.groupviewmodel.GroupsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddGroupFragment : Fragment(R.layout.fragment_add_group) {
    private lateinit var binding: FragmentAddGroupBinding
    private val groupsViewModel: GroupsViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddGroupBinding.bind(view)

        addGroup()
    }

    private fun addGroup() {
        binding.btnNext.setOnClickListener {
            val groupName = binding.etGroupName.text.toString()
            val currentTimeMillis = System.currentTimeMillis().toString()
            if (groupName.isNotEmpty()) {
                val result = lifecycleScope.launch {
                    groupsViewModel.addGroup(
                        ChatData(
                            groupPath = currentTimeMillis,
                            groupName = groupName,
                            groupId = currentTimeMillis,
                            lastSms = "Hello"
                        )
                    )
                }
                if (result.isCompleted) {
                    findNavController().navigate(R.id.action_addGroupFragment_to_mainFragment)
                } else {
                    Toast.makeText(requireContext(), "Please try again later", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}