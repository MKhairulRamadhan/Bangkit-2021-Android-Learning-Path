package com.mkhairulramadhan.favorite.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.core.adapter.MoviesAdapter
import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.favorite.viewModel.FavoriteViewModel
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentMoviesBinding
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.MoviesFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFavoriteFragment: Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recyclerView
        binding.rvMovie.setHasFixedSize(true)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        loading(true)
        binding.rvMovie.layoutManager = LinearLayoutManager(context)
        adapter = MoviesAdapter()
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
            loading(false)
            if(it.isNotEmpty()){
                binding.notFound.visibility = View.GONE
            }else{
                binding.notFound.visibility = View.VISIBLE
            }
        })
        binding.rvMovie.adapter = adapter

        //when item selected
        adapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieModel) {
                selectedMovie(data)
            }
        })
    }

    private fun selectedMovie(data: MovieModel){
        val moveDetail = Intent(context, DetailMovieTvActivity::class.java)
        moveDetail.putExtra(DetailMovieTvActivity.EXTRA_ID,data.id)
        moveDetail.putExtra(DetailMovieTvActivity.EXTRA_TYPE, MoviesFragment.TYPE_MOVIE)
        startActivity(moveDetail)
    }

    private fun loading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

}