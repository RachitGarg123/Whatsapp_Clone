package com.example.whatsappclone.chat.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityChatBinding
import com.example.whatsappclone.home.data.dto.ChatInfo
import com.example.whatsappclone.home.domain.constants.HomeConstants

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        setUpNavGraph()
    }

    private fun setUpNavGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvChat) as NavHostFragment
        val navController = navHostFragment.navController
        val navInflater = navController.navInflater
        val chatNavGraph = navInflater.inflate(R.navigation.nav_graph_chat)
        chatNavGraph.setStartDestination(R.id.chatFragment)
        val chatInfo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(HomeConstants.CHAT_INFO, ChatInfo::class.java)
        } else {
            intent?.getParcelableExtra(HomeConstants.CHAT_INFO) as ChatInfo?
        }
        val bundle = Bundle()
        bundle.putParcelable(HomeConstants.CHAT_INFO, chatInfo)
        navController.setGraph(chatNavGraph, bundle)
    }
}