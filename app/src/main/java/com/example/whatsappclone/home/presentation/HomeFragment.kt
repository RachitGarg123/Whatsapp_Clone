package com.example.whatsappclone.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.whatsappclone.core.presentation.BaseFragment
import com.example.whatsappclone.databinding.FragmentHomeBinding
import com.example.whatsappclone.home.domain.HomeFragmentViewModel
import com.example.whatsappclone.home.presentation.adapter.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.populateTabsTextList()
        binding.viewPagerHome.adapter = HomeViewPagerAdapter(fragment = this@HomeFragment, tabCount = 3)
        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tab, position ->
            tab.text = viewModel.getTabText(position)
        }.attach()
    }
}