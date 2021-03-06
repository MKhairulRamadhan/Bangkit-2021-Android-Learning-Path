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
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentMoviesBinding
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_ID
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_TYPE
import com.mkhairulramadhan.submission1moviecatalog.viewModel.MovieTvViewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("dataku", "create movie fragment")
        //progressBar
        binding.progressBar.visibility = View.VISIBLE

        //viewModel
        viewModel = ViewModelProvider(this)[MovieTvViewModel::class.java]
        val listMovie = viewModel.getMovieData()

        //recyclerView
        binding.rvMovie.setHasFixedSize(true)
        showRecyclerView(listMovie)
    }

    private fun showRecyclerView(listMovie: ArrayList<MovieTvModel>) {
        binding.rvMovie.layoutManager = LinearLayoutManager(context)
        val adapter = MoviesTvAdapter()
        adapter.setData(listMovie)
        binding.rvMovie.adapter = adapter
        binding.progressBar.visibility = View.GONE

        //when item selected
        adapter.setOnItemClickCallback(object : MoviesTvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieTvModel) {
                selectedMovie(data)
            }
        })
    }

    private fun selectedMovie(data: MovieTvModel){
        val moveDetail = Intent(context, DetailMovieTvActivity::class.java)
        moveDetail.putExtra(EXTRA_ID,data.id)
        moveDetail.putExtra(EXTRA_TYPE, TYPE_MOVIE)
        startActivity(moveDetail)
    }

    companion object {
        const val TYPE_MOVIE = "type_movie"

        fun newInstance(): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}