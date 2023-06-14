package com.example.telegramdemo.data.models

data class UserData(
    val userId: String,
    val phoneNumber: String,
    val userName: String,
    val userSurname: String? = null
)