package com.example.firstcomposeproject

import android.app.Activity
import android.os.Bundle
import com.example.thirdcomposeproject.databinding.ActivityDashBoardMainBinding


class DashBoardMainActivity : Activity() {

    lateinit var binding: ActivityDashBoardMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}