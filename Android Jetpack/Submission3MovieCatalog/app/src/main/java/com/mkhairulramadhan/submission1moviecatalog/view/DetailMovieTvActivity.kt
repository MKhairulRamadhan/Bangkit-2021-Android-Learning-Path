package com.mkhairulramadhan.submission1moviecatalog.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mkhairulramadhan.submission1moviecatalog.databinding.ActivityDetailMovieTvBinding
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.MoviesFragment.Companion.TYPE_MOVIE
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.TvShowFragment.Companion.TYPE_TV
import com.mkhairulramadhan.submission1moviecatalog.viewModel.DetailViewModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.ViewModelFactory
import java.util.*

class DetailMovieTvActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailMovieTvBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //progressBar
        binding.progressBar.visibility = View.VISIBLE

        //viewModel
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        // progressBar
        checkIsLoading(true)

        //collapse layour setting
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        //get intent and viewModel data
        val id = intent.getIntExtra(EXTRA_ID,0)
        val type = intent.getStringExtra(EXTRA_TYPE)
        if (type.equals(TYPE_MOVIE, ignoreCase = true)){
            viewModel.getDetailMovie(id).observe(this, {
                //bind to view
                bindDataDetail(it)
            })
        }else if(type.equals(TYPE_TV, ignoreCase = true)){
            viewModel.getDetailTv(id).observe(this,{
                //bind to view
                bindDataDetail(it)
            })
        }

    }

    private fun checkIsLoading(data: Boolean) {
        if (data){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun bindDataDetail(data: MovieTvModel) {
        with(binding){
            backdropImageDetail.loadImage("https://image.tmdb.org/t/p/original/${data.backDropImage}")
            posterImageDetail.loadImage("https://image.tmdb.org/t/p/original/${data.posterImage}")
            titleDetail.text = data.title
            starDetail.text = data.star
            yearDetail.text = data.year
            synopsisDetail.text = data.synopsis
            genreDetail.text = data.tag
            durationDetail.text = data.duration
            languageDetail.text = data.language
            toolbar.title = data.title
            progressBar.visibility = View.GONE
        }
        checkIsLoading(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun ImageView.loadImage(data: String){
        Glide.with(applicationContext)
            .load(data)
            .into(this)
    }

}