package com.mkhairulramadhan.submission1moviecatalog.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.core.adapter.MoviesAdapter
import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.core.valueObject.StatusData
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentMoviesBinding
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_ID
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_TYPE
import com.mkhairulramadhan.submission1moviecatalog.viewModel.MovieTvViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MovieTvViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        binding.rvMovie.layoutManager = LinearLayoutManager(context)
        val adapter = MoviesAdapter()
        viewModel.getMovieData.observe(viewLifecycleOwner, {
            if (it != null){
                when (it.statusData){
                    StatusData.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    StatusData.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { it1 -> adapter.setData(it1) }
                        adapter.notifyDataSetChanged()
                    }
                    StatusData.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Maaf terjadi kesalahan", Toast.LENGTH_LONG).show()
                    }
                }
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