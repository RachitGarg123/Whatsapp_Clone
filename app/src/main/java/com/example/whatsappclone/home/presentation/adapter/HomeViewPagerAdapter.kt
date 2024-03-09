package com.example.whatsappclone.home.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whatsappclone.home.presentation.HomeCallsFragment
import com.example.whatsappclone.home.presentation.HomeChatFragment
import com.example.whatsappclone.home.presentation.HomeUpdatesFragment

class HomeViewPagerAdapter(fragment: Fragment, private val tabCount: Int): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeChatFragment()
            1 -> HomeUpdatesFragment()
            2 -> HomeCallsFragment()
            else -> Fragment()
        }
    }
}