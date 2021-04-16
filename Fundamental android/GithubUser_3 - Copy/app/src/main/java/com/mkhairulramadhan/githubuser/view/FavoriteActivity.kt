package com.mkhairulramadhan.githubuser.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.githubuser.R
import com.mkhairulramadhan.githubuser.adapter.UserAdapter
import com.mkhairulramadhan.githubuser.databinding.ActivityFavoriteBinding
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.viewModel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //button back
        binding.buttonBack.setOnClickListener(this)

        //viewModel
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        viewModel.setFavoriteListUser(applicationContext)

        //recyclerView
        binding.rvFavoriteList.setHasFixedSize(true)
        showRecyclerViewList()
    }

    private fun showRecyclerViewList() {
        binding.rvFavoriteList.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(this)
        viewModel.favoriteListUser.observe(this, {
            userAdapter.setUserList(it.toList())
        })
        binding.rvFavoriteList.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Items) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(data: Items) {
        val moveToDetailActivity = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.USER_EXTRA, data)
        }
        startActivity(moveToDetailActivity)
    }

    override fun onResume() {
        viewModel.setFavoriteListUser(applicationContext)
        super.onResume()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_back -> {
                onBackPressed()
            }
        }
    }

}