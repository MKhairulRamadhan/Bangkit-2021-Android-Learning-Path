package com.mkhairulramadhan.githubuser.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.githubuser.R
import com.mkhairulramadhan.githubuser.adapter.UserAdapter
import com.mkhairulramadhan.githubuser.databinding.ActivityMainBinding
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.viewModel.MainActivityViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("dataku", "create new")
        //date
        val date = Date()
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy",  Locale.ROOT)
        val resultDate = formatter.format(date)
        binding.date.text = resultDate

        //button setting
        binding.setting.setOnClickListener(this)

        //favorite button
        binding.favorite.setOnClickListener(this)

        //viewModel
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        //searchView
        searchUser()

        //progressBar
        showProgressBar()

        //recyclerView
        binding.rvUserList.setHasFixedSize(true)
        showRecyclerViewList()


    }

    private fun searchUser() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()){
                        viewModel.getUser(query.toString())
                        binding.searchView.clearFocus()
                        setNotFoundImage(false)
                    }else{
                        binding.searchView.clearFocus()
                        setNotFoundImage(true)
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false

        })
    }

    private fun showProgressBar() {
        viewModel.progressBar.observe(this, {
            if (it)
                binding.progressBarMain.visibility = VISIBLE
            else
                binding.progressBarMain.visibility = GONE
        })
    }

    private fun showRecyclerViewList() {
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(this)
        viewModel.userList.observe(this, {
            userAdapter.setUserList(it)
            setNotFoundImage(it.isEmpty())
        })
        binding.rvUserList.adapter = userAdapter

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

    private fun setNotFoundImage(status: Boolean){
        binding.rvUserList.visibility = if (status) GONE else VISIBLE
        binding.imageNotFound.visibility = if (status) VISIBLE else GONE
        binding.textNotFound1.visibility = if (status) VISIBLE else GONE
        binding.textNotFound2.visibility = if (status) VISIBLE else GONE
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
            R.id.favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
    }

}