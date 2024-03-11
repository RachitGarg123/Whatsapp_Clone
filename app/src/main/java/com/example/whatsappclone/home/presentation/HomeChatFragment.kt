package com.example.whatsappclone.home.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsappclone.chat.presentation.ChatActivity
import com.example.whatsappclone.databinding.FragmentHomeChatBinding
import com.example.whatsappclone.home.data.dto.ChatInfo
import com.example.whatsappclone.home.domain.constants.HomeConstants
import com.example.whatsappclone.home.presentation.adapter.HomeChatAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeChatFragment : Fragment() {

    private lateinit var binding: FragmentHomeChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHomeChats.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = HomeChatAdapter {
            Intent(requireActivity(), ChatActivity::class.java).apply {
                putExtra(HomeConstants.CHAT_INFO, it)
                startActivity(this)
            }
        }
        binding.rvHomeChats.adapter = adapter
        val firebaseDb = FirebaseFirestore.getInstance()
        firebaseDb.collection(HomeConstants.CHAT_INFO)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val chatInfoList: MutableList<ChatInfo> = mutableListOf()
                    for (document in task.result) {
                        val map = document.data
                        chatInfoList.add(
                            ChatInfo(
                                phoneNumber = map["id"]?.toString(),
                                profileImage = map["profileImage"].toString(),
                                personName = map["personName"].toString(),
                                lastMessage = map["lastMessage"].toString(),
                                lastMessageStatus = null,
                                numberOfUnseenMessages = map["numberOfUnseenMessages"].toString(),
                                lastMessageTime = map["lastMessageTime"].toString()
                            )
                        )
                        Log.d("Firebase", document.id + " => " + document.data)
                    }
                    Log.i("Firebase", "chatInfoList ----> $chatInfoList")
                    adapter.setChatInfoList(chatInfoList)
                } else {
                    Log.w("Firebase", "Error getting documents.", task.exception)
                }
            }
    }
}