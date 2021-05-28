package com.mkhairulramadhan.favorite.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.core.adapter.TvAdapter
import com.mkhairulramadhan.core.domain.model.TvModel
import com.mkhairulramadhan.favorite.viewModel.FavoriteViewModel
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentTvShowBinding
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.TvShowFragment.Companion.TYPE_TV
import org.koin.android.viewmodel.ext.android.viewModel

class TvFavoriteFragment: Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recyclerView
        binding.rvTv.setHasFixedSize(true)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        loading(true)
        binding.rvTv.layoutManager = LinearLayoutManager(context)
        adapter = TvAdapter()
        viewModel.getFavoriteTv().observe(viewLifecycleOwner, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
            loading(false)
            if(it.isNotEmpty()){
                binding.notFound.visibility = View.GONE
            }else{
                binding.notFound.visibility = View.VISIBLE
            }
        })
        binding.rvTv.adapter = adapter

        //when item selected
        adapter.setOnItemClickCallback(object : TvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvModel) {
                selectedMovie(data)
            }
        })
    }

    private fun selectedMovie(data: TvModel){
        val moveDetail = Intent(context, DetailMovieTvActivity::class.java)
        moveDetail.putExtra(DetailMovieTvActivity.EXTRA_ID,data.id)
        moveDetail.putExtra(DetailMovieTvActivity.EXTRA_TYPE, TYPE_TV)
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