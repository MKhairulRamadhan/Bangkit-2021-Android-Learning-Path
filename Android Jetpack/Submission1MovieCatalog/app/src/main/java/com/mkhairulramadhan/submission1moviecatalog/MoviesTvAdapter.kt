package com.mkhairulramadhan.submission1moviecatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkhairulramadhan.submission1moviecatalog.databinding.ListItemBinding

class MoviesTvAdapter: RecyclerView.Adapter<MoviesTvAdapter.ListViewHolder>() {

    lateinit var  listMoviesTvs: ArrayList<MovieTvModel>

    fun setData(listMoviesTv: ArrayList<MovieTvModel>){
        this.listMoviesTvs = listMoviesTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMoviesTvs[position])
    }

    override fun getItemCount(): Int = listMoviesTvs.size

    class ListViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieTvModel: MovieTvModel) {
            with(binding){

            }
        }
    }

}