package com.mkhairulramadhan.submission1moviecatalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): MoviesFragment{
            val fragment = MoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}