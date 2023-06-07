package com.example.telegramdemo.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.telegramdemo.R
import com.example.telegramdemo.data.models.MessageData
import com.example.telegramdemo.databinding.FragmentChatBinding
import com.example.telegramdemo.ui.adapter.MessageAdapter
import com.example.telegramdemo.utils.getDeviceName
import com.example.telegramdemo.utils.toTimeFormat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment(R.layout.fragment_chat) {
    private lateinit var binding: FragmentChatBinding
    private var adapter = MessageAdapter()
    private lateinit var database: FirebaseDatabase
    private lateinit var pref: SharedPreferences
    private var list = mutableListOf<MessageData>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        pref = requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        binding.recyclerView.adapter = adapter

        database = Firebase.database

        adapter.currentList.forEach {
            Log.d(
                "TTTT", "Message: ${it.username}\nBy: ${it.message}\nTime: ${it.time}\nID: ${it.id}"
            )
        }

        sendMessage()

        getMessages()
    }

    private fun getMessages() {
        val myRef = database.getReference("group001")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    list.clear()
                    snapshot.children.forEach {
                        val item = it.value as HashMap<*, *>
                        list.add(
                            MessageData(
                                message = item["message"].toString(),
                                time = item["time"].toString(),
                                username = item["username"].toString(),
                                id = item["id"].toString()
                            )
                        )
                    }
                    adapter.submitList(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.d("TTTT", "QATE")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TTTT", "QATEEEEEEEEEE")
            }
        })
    }

    private fun sendMessage() {
        binding.ivSend.setOnClickListener {
            val message = binding.editText.text.toString()
            if (message.isNotEmpty()) {
                val currentTimeMillis = System.currentTimeMillis()
                val messageData = MessageData(
                    id = currentTimeMillis.toString(),
                    message = message,
                    time = toTimeFormat(currentTimeMillis),
                    username = pref.getString("username", getDeviceName()).toString()
                )
                database.getReference("group001").child(currentTimeMillis.toString())
                    .setValue(messageData)
                    .addOnSuccessListener {
                        binding.editText.setText("")
                    }.addOnFailureListener {
                        binding.editText.error = binding.editText.text.toString()
                    }
            }
        }
    }
}