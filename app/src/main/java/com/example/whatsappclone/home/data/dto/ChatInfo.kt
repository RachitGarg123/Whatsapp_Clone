package com.example.whatsappclone.home.data.dto

import android.os.Parcelable
import com.example.whatsappclone.home.data.enums.MessageStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatInfo(
    val phoneNumber: String? = null,
    val profileImage: String? = null,
    val personName: String? = null,
    val lastMessage: String? = null,
    val lastMessageStatus: MessageStatus? = MessageStatus.NOT_RECEIVED,
    val numberOfUnseenMessages: String? = null,
    val lastMessageTime: String? = null
): Parcelable