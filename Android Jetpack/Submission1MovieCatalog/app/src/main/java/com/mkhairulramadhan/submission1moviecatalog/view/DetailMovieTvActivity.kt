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
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        //collapse layour setting
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        //get intent and viewModel data
        val id = intent.getStringExtra(EXTRA_ID)
        val type = intent.getStringExtra(EXTRA_TYPE)
        lateinit var dataDetail: MovieTvModel
        if (type.equals(TYPE_MOVIE, ignoreCase = true)){
            id?.let { viewModel.setMovieId(it) }
            dataDetail = viewModel.getDetailMovie()
        }else if(type.equals(TYPE_TV, ignoreCase = true)){
            id?.let {viewModel.setTvId(it)}
            dataDetail = viewModel.getDetailTv()
        }

        //bind to view
        bindDataDetail(dataDetail)
    }

    private fun bindDataDetail(data: MovieTvModel) {
        with(binding){
            titleDetail.text = data.title
            directorDetail.text = data.director
            starDetail.text = data.star
            yearDetail.text = data.year
            durationDetial.text = data.duration
            ageDetial.text = data.age
            synopsisDetail.text = data.synopsis
            genreDetail.text = data.tag
            toolbar.title = data.title
            imageDetail.loadImage(data.image)
            progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun ImageView.loadImage(data: Int){
        Glide.with(applicationContext)
            .load(data)
            .into(this)
    }

}