package com.example.whatsappclone.chat.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.whatsappclone.R
import com.example.whatsappclone.core.domain.utils.AppUtils.visible
import com.example.whatsappclone.databinding.FragmentChatBinding
import com.example.whatsappclone.home.data.dto.ChatInfo
import com.example.whatsappclone.home.domain.constants.HomeConstants

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private var chatInfo: ChatInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundle()
        setUpUI()
    }

    private fun getBundle() {
        chatInfo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(HomeConstants.CHAT_INFO, ChatInfo::class.java)
        } else {
            arguments?.getParcelable(HomeConstants.CHAT_INFO) as ChatInfo?
        }
    }

    private fun setUpUI() {
        binding.toolbarChat.ivBackToolbar.visible()
        binding.toolbarChat.ivProfileToolbar.visible()
        binding.toolbarChat.tvTitleToolbar.text = chatInfo?.personName
        Glide.with(requireContext())
            .load(chatInfo?.profileImage)
            .circleCrop()
            .placeholder(R.drawable.filled_person_placeholder_30)
            .into(binding.toolbarChat.ivProfileToolbar)
        binding.toolbarChat.ivCameraToolbar.setImageResource(R.drawable.filled_video_camera_white_24)
        binding.toolbarChat.ivSearchToolbar.setImageResource(R.drawable.filled_phone_call_white_24)
    }
}