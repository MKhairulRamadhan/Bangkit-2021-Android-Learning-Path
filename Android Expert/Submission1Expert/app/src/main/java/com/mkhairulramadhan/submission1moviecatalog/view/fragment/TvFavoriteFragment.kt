package com.mkhairulramadhan.submission1moviecatalog.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.adapter.TvAdapter
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentTvShowBinding
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.TvShowFragment.Companion.TYPE_TV
import com.mkhairulramadhan.submission1moviecatalog.viewModel.FavoriteViewModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.ViewModelFactory

class TvFavoriteFragment: Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: FavoriteViewModel
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

        //paging touch
        itemTouchData.attachToRecyclerView(binding.rvTv)

        //viewModel
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        //recyclerView
        binding.rvTv.setHasFixedSize(true)
        showRecyclerView()
    }

    private fun showRecyclerView() {
        loading(true)
        binding.rvTv.layoutManager = LinearLayoutManager(context)
        adapter = TvAdapter()
        viewModel.getFavoriteTv().observe(viewLifecycleOwner, {
            adapter.submitList(it)
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
            override fun onItemClicked(data: TvEntity) {
                selectedMovie(data)
            }
        })
    }

    private fun selectedMovie(data: TvEntity){
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

    private val itemTouchData = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvEntity = adapter.getSwiped(swipedPosition)
                tvEntity?.let { viewModel.setFavoriteTv(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_cancel, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_yes) { _ ->
                    tvEntity?.let { viewModel.setFavoriteTv(it) }
                }
                snackbar.show()
            }
        }
    })
}