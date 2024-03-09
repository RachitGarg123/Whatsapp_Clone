package com.example.whatsappclone.home.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.whatsappclone.R
import com.example.whatsappclone.core.presentation.BaseActivity
import com.example.whatsappclone.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@HomeActivity, R.layout.activity_home)
        setUpNavGraph()
    }

    private fun setUpNavGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvHome) as NavHostFragment
        val navController = navHostFragment.navController
        val navInflater = navController.navInflater
        val homeNavGraph = navInflater.inflate(R.navigation.nav_graph_home)
        homeNavGraph.setStartDestination(R.id.homeFragment)
        navController.graph = homeNavGraph
    }
}