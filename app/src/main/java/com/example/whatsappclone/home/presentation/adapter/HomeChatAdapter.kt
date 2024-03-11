package com.example.whatsappclone.home.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.AdapterHomeChatsBinding
import com.example.whatsappclone.home.data.dto.ChatInfo
import com.example.whatsappclone.home.data.enums.MessageStatus

class HomeChatAdapter(private val openChat: (ChatInfo)-> Unit): RecyclerView.Adapter<HomeChatAdapter.ChatsViewHolder>() {

    private val chatInfoList: MutableList<ChatInfo> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setChatInfoList(chatInfoList: List<ChatInfo>) {
        this.chatInfoList.clear()
        this.chatInfoList.addAll(chatInfoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val binding = AdapterHomeChatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(binding)
    }

    override fun getItemCount(): Int = chatInfoList.size

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ChatsViewHolder(private val binding: AdapterHomeChatsBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            Log.i("Firebase", "Reached here in ChatsViewHolder")
            binding.tvName.text = chatInfoList[position].personName
            binding.tvLastMessage.text = chatInfoList[position].lastMessage
            val lastMessageStatus = when(chatInfoList[position].lastMessageStatus) {
                MessageStatus.READ -> { R.drawable.filled_read_msg_double_blue_tick_10 }
                MessageStatus.RECEIVED_NOT_READ -> { R.drawable.filled_unread_received_msg_double_tick_10 }
                MessageStatus.NOT_RECEIVED, null -> { R.drawable.filled_unread_not_received_msg_tick_10 }
            }
            Glide.with(binding.root.context)
                .load(lastMessageStatus)
                .placeholder(R.drawable.filled_person_placeholder_30)
                .into(binding.ivSendMessageStatus)
            Glide.with(binding.root.context)
                .load(chatInfoList[position].profileImage?.toUri())
                .circleCrop()
                .placeholder(R.drawable.filled_person_placeholder_30)
                .into(binding.ivProfile)
            binding.tvNumberOfUnseenMessages.text = chatInfoList[position].numberOfUnseenMessages
            binding.tvLastMessageTime.text = chatInfoList[position].lastMessageTime
            binding.root.setOnClickListener {
                openChat(chatInfoList[position])
            }
        }
    }
}