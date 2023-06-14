package com.example.telegramdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.telegramdemo.R
import com.example.telegramdemo.databinding.FragmentContactsBinding
import com.example.telegramdemo.presentation.contactviewmodel.ContactsViewModel
import com.example.telegramdemo.ui.adapter.ContactAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private lateinit var binding: FragmentContactsBinding
    private val contactsViewModel: ContactsViewModel by viewModel()
    private val adapter = ContactAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactsBinding.bind(view)

        initObservers()

        swipeRefreshLayout()
    }

    private fun swipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            lifecycleScope.launch {
                contactsViewModel.getAllContacts()
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initObservers() {
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch {
            contactsViewModel.getAllContactsLiveData.observe(requireActivity()) {
                adapter.submitList(it)
            }
        }
        lifecycleScope.launch {
            contactsViewModel.getAllContacts()
        }
    }
}