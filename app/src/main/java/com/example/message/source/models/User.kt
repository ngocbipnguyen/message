package com.example.message.source.models

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val uid: String,
    val displayName: String,
    val email: String,
    val photoUrl: String,
    val pushToken: String
)
