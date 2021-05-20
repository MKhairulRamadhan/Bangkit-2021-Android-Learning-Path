package com.mkhairulramadhan.submission1moviecatalog.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.submission1moviecatalog.adapter.TvAdapter
import com.mkhairulramadhan.submission1moviecatalog.data.local.entity.TvEntity
import com.mkhairulramadhan.submission1moviecatalog.databinding.FragmentTvShowBinding
import com.mkhairulramadhan.submission1moviecatalog.valueObject.StatusData
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_ID
import com.mkhairulramadhan.submission1moviecatalog.view.DetailMovieTvActivity.Companion.EXTRA_TYPE
import com.mkhairulramadhan.submission1moviecatalog.viewModel.MovieTvViewModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.ViewModelFactory


class TvShowFragment : Fragment() {

    companion object{
        const val TYPE_TV = "type_tv"
    }
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieTvViewModel::class.java]

        //recyclerView
        binding.rvTv.setHasFixedSize(true)
        showRecyclerView()
    }


    private fun selectedTv(data: TvEntity){
        val moveDetail = Intent(context, DetailMovieTvActivity::class.java)
        moveDetail.putExtra(EXTRA_ID,data.id)
        moveDetail.putExtra(EXTRA_TYPE, TYPE_TV)
        startActivity(moveDetail)
    }

    private fun showRecyclerView() {
        binding.rvTv.layoutManager = LinearLayoutManager(context)
        val adapter = TvAdapter()
        viewModel.getTvData().observe(viewLifecycleOwner, {
            if (it != null){
                when (it.statusData){
                    StatusData.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    StatusData.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitList(it.data)
                        adapter.notifyDataSetChanged()
                    }
                    StatusData.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Maaf terjadi kesalahan", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        binding.rvTv.adapter = adapter

        //when item selected
        adapter.setOnItemClickCallback(object : TvAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvEntity) {
                selectedTv(data)
            }
        })
    }

}