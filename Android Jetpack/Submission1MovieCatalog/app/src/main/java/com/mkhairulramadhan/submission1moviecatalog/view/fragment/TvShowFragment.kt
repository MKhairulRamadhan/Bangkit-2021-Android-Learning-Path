package com.mkhairulramadhan.submission1moviecatalog.view.fragment

import android.content.Intent
import android.os.Bundle
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
        viewModel = ViewModelProvider(this)[MovieTvViewModel::class.java]
        val listTv = viewModel.getTvData()

        //recyclerView
        binding.rvTv.setHasFixedSize(true)
        showRecyclerView(listTv)
    }

    private fun selectedTv(data: MovieTvModel){
        val moveDetail = Intent(context, DetailMovieTvActivity::class.java)
        moveDetail.putExtra(EXTRA_ID,data.id)
        moveDetail.putExtra(EXTRA_TYPE, TYPE_TV)
        startActivity(moveDetail)
    }

    private fun showRecyclerView(listTv: ArrayList<MovieTvModel>) {
        binding.rvTv.layoutManager = LinearLayoutManager(context)
        val adapter = MoviesTvAdapter()
        adapter.setData(listTv)
        binding.rvTv.adapter = adapter

        //when item selected
        adapter.setOnItemClickCallback(object : MoviesTvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTvModel) {
                selectedTv(data)
            }
        })
    }

}