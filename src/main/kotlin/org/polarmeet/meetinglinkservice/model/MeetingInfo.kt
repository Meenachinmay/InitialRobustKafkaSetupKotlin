package org.polarmeet.meetinglinkservice.model

data class MeetingInfo(
    val id: String,
    val title: String,
    val description: String,
    val startTime: String? = null,
    val endTime: String? = null,
    val meetingLink: String? = null
)
{
    // Add a no-args constructor for Jackson deserialization
    constructor() : this("", "", "")
}