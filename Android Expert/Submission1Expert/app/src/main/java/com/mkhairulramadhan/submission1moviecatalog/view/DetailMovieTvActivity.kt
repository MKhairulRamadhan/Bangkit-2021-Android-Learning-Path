package com.mkhairulramadhan.submission1moviecatalog.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.core.domain.model.MovieModel
import com.mkhairulramadhan.core.domain.model.TvModel
import com.mkhairulramadhan.core.valueObject.ResourceData
import com.mkhairulramadhan.core.valueObject.StatusData
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.databinding.ActivityDetailMovieTvBinding
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.MoviesFragment.Companion.TYPE_MOVIE
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.TvShowFragment.Companion.TYPE_TV
import com.mkhairulramadhan.submission1moviecatalog.viewModel.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailMovieTvActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailMovieTvBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //progressBar
        binding.progressBar.visibility = View.VISIBLE

        //collapse layour setting
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        //get intent and viewModel data
        val id = intent.getIntExtra(EXTRA_ID,0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        //favorite button
        binding.favoriteButton.setOnClickListener {
            if (type.equals(TYPE_MOVIE, ignoreCase = true)){
                viewModel.setMovieFavorire()
            }else if(type.equals(TYPE_TV, ignoreCase = true)){
                viewModel.setTvFavorire()
            }
        }

        if (type.equals(TYPE_MOVIE, ignoreCase = true)){
            viewModel.setSelectMovie(id)
            viewModel.allMovie.observe(this) {
                if (it != null){
                    when(it.statusData){
                        StatusData.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        StatusData.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            bindDataDetailMovie(it)
                        }
                        StatusData.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Maaf terjadi kesalahan.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }else if(type.equals(TYPE_TV, ignoreCase = true)){
            viewModel.setSelectTv(id)
            viewModel.allTv.observe(this) {
                if (it != null){
                    when(it.statusData){
                        StatusData.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        StatusData.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            bindDataDetailTv(it)
                        }
                        StatusData.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Maaf terjadi kesalahan.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    }


    private fun bindDataDetailMovie(data: ResourceData<MovieModel>) {
        with(binding){
            backdropImageDetail.loadImage("https://image.tmdb.org/t/p/original/${data.data?.backDropImage}")
            posterImageDetail.loadImage("https://image.tmdb.org/t/p/original/${data.data?.posterImage}")
            titleDetail.text = data.data?.title
            starDetail.text = data.data?.star
            yearDetail.text = data.data?.year
            synopsisDetail.text = data.data?.synopsis
            languageDetail.text = data.data?.language
            toolbar.title = data.data?.title
            progressBar.visibility = View.GONE
            data.data?.favorite?.let { setIconLoveState(it) }
        }
    }

    private fun bindDataDetailTv(data: ResourceData<TvModel>) {
        with(binding){
            backdropImageDetail.loadImage("https://image.tmdb.org/t/p/original/${data.data?.backDropImage}")
            posterImageDetail.loadImage("https://image.tmdb.org/t/p/original/${data.data?.posterImage}")
            titleDetail.text = data.data?.name
            starDetail.text = data.data?.star
            yearDetail.text = data.data?.year
            synopsisDetail.text = data.data?.synopsis
            languageDetail.text = data.data?.language
            toolbar.title = data.data?.name
            progressBar.visibility = View.GONE
            data.data?.favorite?.let { setIconLoveState(it) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun ImageView.loadImage(data: String){
        Glide.with(applicationContext)
            .load(data)
            .apply(RequestOptions.placeholderOf(R.drawable.loading_image).error(R.drawable.error_image))
            .into(this)
    }

    private fun setIconLoveState(state: Boolean){
        if (state){
            binding.favoriteButton.setImageResource(R.drawable.love_full)
        }else{
            binding.favoriteButton.setImageResource(R.drawable.love_not_full)
        }
    }

}