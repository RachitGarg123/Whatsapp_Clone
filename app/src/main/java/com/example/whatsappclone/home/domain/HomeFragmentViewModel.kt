package com.example.whatsappclone.home.domain

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(): ViewModel() {

    private val tabsTexts: MutableList<String> = mutableListOf()

    fun populateTabsTextList() {
        tabsTexts.add("Chats")
        tabsTexts.add("Updates")
        tabsTexts.add("Calls")
    }

    fun getTabText(position: Int) = tabsTexts[position]
}