package org.polarmeet.meetinglinkservice.model

data class Message(
    val id: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)