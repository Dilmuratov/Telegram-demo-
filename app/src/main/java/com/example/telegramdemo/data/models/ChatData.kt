package com.example.telegramdemo.data.models

data class ChatData(
    /**Path in FireStore*/
    var groupId: String,
    /**Path in Realtime database*/
    val groupPath: String,
    /**Name of User created*/
    var groupName: String,
    /**Last sms*/
    var lastSms: String = "Start messaging",
)
