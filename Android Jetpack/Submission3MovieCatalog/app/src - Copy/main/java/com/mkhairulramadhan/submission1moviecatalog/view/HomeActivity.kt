package com.mkhairulramadhan.submission1moviecatalog.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mkhairulramadhan.submission1moviecatalog.adapter.PagerAdapter
import com.mkhairulramadhan.submission1moviecatalog.databinding.ActivityMainBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = PagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}