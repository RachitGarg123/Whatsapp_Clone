package com.example.whatsappclone.home.data.dto

import com.example.whatsappclone.home.data.enums.MessageStatus

data class ChatInfo(
    val id: Int = 0,
    val profileImage: String? = null,
    val personName: String? = null,
    val lastMessage: String? = null,
    val lastMessageStatus: MessageStatus? = MessageStatus.NOT_RECEIVED,
    val numberOfUnseenMessages: String? = null,
    val lastMessageTime: String? = null
)