package com.example.telegramdemo.data.models

data class ChatData(
    var groupName: String,
    var path: String,
    var lastSms: String = "Start messaging",
)
