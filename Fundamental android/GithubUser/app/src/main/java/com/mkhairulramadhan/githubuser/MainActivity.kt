package com.mkhairulramadhan.githubuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.githubuser.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //date
        val date = Date()
        val formatter = SimpleDateFormat("EEEE, dd MMMM YYYY")
        val result_date = formatter.format(date)
        binding.date.text = result_date

        //recyclerView
        binding.rvUserList.setHasFixedSize(true)

        list.addAll(getListUsers())
        showRecyclerViewList()
    }

    private fun showRecyclerViewList() {
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(list)
        binding.rvUserList.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserModel) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(data: UserModel) {
        val moveToDetailActivity = Intent(this, DetailActivity::class.java)
        moveToDetailActivity.putExtra(DetailActivity.USER_EXTRA, data)
        startActivity(moveToDetailActivity)
    }

    private fun getListUsers(): ArrayList<UserModel> {
        val dataName = resources.getStringArray(R.array.name)
        val dataUserName = resources.getStringArray(R.array.username)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollowers = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)

        val listUser = ArrayList<UserModel>()
        for(position in dataName.indices){
            val user = UserModel(
                    dataUserName[position],
                    dataName[position],
                    dataLocation[position],
                    dataRepository[position],
                    dataCompany[position],
                    dataFollowers[position],
                    dataFollowing[position],
                    dataAvatar.getResourceId(position, -1)
            )
            listUser.add(user)
        }
        return listUser
    }

}