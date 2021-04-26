package com.mkhairulramadhan.submission1moviecatalog.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.flaviofaria.kenburnsview.KenBurnsView
import com.mkhairulramadhan.submission1moviecatalog.databinding.ActivityDetailMovieTvBinding
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.DetailViewModel
import java.util.*

class DetailMovieTvActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailMovieTvBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        //collapse layour setting
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        //get intent data
        val data = intent.getParcelableExtra<MovieTvModel>(EXTRA_DATA)
        data?.let { viewModel.setDetailMovieTv(it) }
        bindDataDetail(viewModel.listDetailMovieTv)
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
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun KenBurnsView.loadImage(data: Int){
        Glide.with(applicationContext)
            .load(data)
            .into(this)
    }

}