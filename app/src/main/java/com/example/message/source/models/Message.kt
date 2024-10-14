package com.example.message.source.models

data class Message(
    val idFrom: String,
    val idTo: String,
    val timestamp: String,
    val content: String,
    val type: String,
) {
    override fun toString(): String {
        return "Message(idFrom='$idFrom', idTo='$idTo', timestamp='$timestamp', content='$content', type='$type')"
    }
}