package com.mkhairulramadhan.favorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mkhairulramadhan.favorite.adapter.PagerAdapter
import com.mkhairulramadhan.favorite.di.favoriteModule
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentFavoriteBinding
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadKoinModules(favoriteModule)
        val pagerAdapter = PagerAdapter(
            requireContext(),
            childFragmentManager
        )
        binding.viewPager.adapter = pagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}