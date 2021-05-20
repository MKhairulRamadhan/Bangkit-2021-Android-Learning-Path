package com.mkhairulramadhan.submission1moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.MovieEntity
import com.mkhairulramadhan.submission1moviecatalog.databinding.ListItemBinding

class MoviesAdapter: PagedListAdapter<MovieEntity, MoviesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: MovieEntity)
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movieItem = getItem(position)
        if(movieItem != null){
            holder.bind(movieItem)
        }
    }

    fun getSwiped(position: Int): MovieEntity? = getItem(position)

    inner class ListViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieEntity) {
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${data.posterImage}")
                    .apply(RequestOptions.placeholderOf(R.drawable.loading_image).error(R.drawable.error_image).override(130,165))
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