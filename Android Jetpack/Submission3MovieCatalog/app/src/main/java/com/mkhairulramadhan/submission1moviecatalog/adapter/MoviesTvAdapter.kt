package com.mkhairulramadhan.submission1moviecatalog.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.submission1moviecatalog.databinding.ListItemBinding
import com.mkhairulramadhan.submission1moviecatalog.model.MovieTvModel

class MoviesTvAdapter: RecyclerView.Adapter<MoviesTvAdapter.ListViewHolder>() {

    private var listMoviesTvs = ArrayList<MovieTvModel>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: MovieTvModel)
    }

    fun setData(listMoviesTv: List<MovieTvModel>){
        this.listMoviesTvs = listMoviesTv as ArrayList<MovieTvModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        listMoviesTvs[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listMoviesTvs.size

    inner class ListViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieTvModel) {
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${data.posterImage}")
                    .apply(RequestOptions().override(130,165))
                    .centerCrop()
                    .into(binding.imageList)
                titleList.text = data.title
                languageList.text = data.language
                yearList.text = data.year
                starList.text = data.star
                synopsisList.text = data.synopsis
                itemView.setOnClickListener{onItemClickCallback?.onItemClicked(data)}
            }
        }
    }

}