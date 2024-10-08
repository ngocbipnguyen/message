package com.example.message.source.models

data class User(
    val uid: String,
    val displayName: String,
    val email: String,
    val photoUrl: String,
    val pushToken: String
)
