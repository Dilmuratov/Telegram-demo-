package com.example.telegramdemo.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.ChatData
import com.example.telegramdemo.databinding.FragmentMainBinding
import com.example.telegramdemo.ui.adapter.ChatAdapter
import com.example.telegramdemo.utils.getDeviceName
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding
    private lateinit var pref: SharedPreferences
    private var adapter = ChatAdapter()
    private var list = mutableListOf<ChatData>()
    private lateinit var firestore: FirebaseFirestore
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        pref = (requireContext()).getSharedPreferences("myPref", Context.MODE_PRIVATE)

        if (pref.getString("username", "").toString().isEmpty()) {
            pref.edit().putString("username", getDeviceName()).apply()
            Log.d("TTTT", pref.getString("username", "").toString())
        }

        binding.recyclerView.adapter = adapter
        firestore = Firebase.firestore
        firestore.collection("groups").get().addOnSuccessListener { result ->
            for (document in result) {
                val chatData = ChatData(
                    path = document.id,
                    groupName = document["name"].toString(),
                    lastSms = document["lastSms"].toString()
                )
                list.add(chatData)
            }
            adapter.submitList(list)
        }

        adapter.setOnItemClickListener { chatData ->
            findNavController().navigate(R.id.action_mainFragment_to_chatFragment)
            findNavController().popBackStack()
        }
//
//
//
//        binding.tvEnter.setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment_to_chatFragment)
//            findNavController().popBackStack()
//        }
    }
}