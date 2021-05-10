package com.mkhairulramadhan.submission1moviecatalog.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.submission1moviecatalog.adapter.MoviesTvAdapter
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentTvShowBinding
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_ID
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_TYPE
import com.mkhairulramadhan.submission1moviecatalog.viewModel.MovieTvViewModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.ViewModelFactory


class TvShowFragment : Fragment() {

    companion object{
        const val TYPE_TV = "type_tv"
    }
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[MovieTvViewModel::class.java]

        //recyclerView
        binding.rvTv.setHasFixedSize(true)
        showRecyclerView()
    }

    private fun checkIsLoading(data: Boolean) {
        if (data){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun selectedTv(data: MovieTvModel){
        val moveDetail = Intent(context, DetailMovieTvActivity::class.java)
        moveDetail.putExtra(EXTRA_ID,data.id)
        moveDetail.putExtra(EXTRA_TYPE, TYPE_TV)
        startActivity(moveDetail)
    }

    private fun showRecyclerView() {
        checkIsLoading(true)
        binding.rvTv.layoutManager = LinearLayoutManager(context)
        val adapter = MoviesTvAdapter()
        viewModel.getTvData.observe(viewLifecycleOwner, {
            adapter.setData(it)
            checkIsLoading(false)
        })
        binding.rvTv.adapter = adapter

        //when item selected
        adapter.setOnItemClickCallback(object : MoviesTvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTvModel) {
                selectedTv(data)
            }
        })
    }

}