package com.example.telegramdemo.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val networkModule = module {
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
    single<FirebaseDatabase> {
        FirebaseDatabase.getInstance()
    }
}