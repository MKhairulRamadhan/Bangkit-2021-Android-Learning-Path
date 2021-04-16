package com.mkhairulramadhan.githubuser.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mkhairulramadhan.githubuser.R
import com.mkhairulramadhan.githubuser.adapter.PagerAdapter
import com.mkhairulramadhan.githubuser.databinding.ActivityDetailBinding
import com.mkhairulramadhan.githubuser.db.helper.MappingHelper
import com.mkhairulramadhan.githubuser.db.network.model.Items
import com.mkhairulramadhan.githubuser.viewModel.DetailActivityViewModel
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity(), View.OnClickListener{

    companion object{
        const val USER_EXTRA = "user_extra"
        @StringRes
        private val TAB_TITLE = intArrayOf(
                R.string.follower,
                R.string.following
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var user :Items
    private var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra(USER_EXTRA)!!

        //button back
        binding.buttonBack.setOnClickListener(this)

        //button change language
        binding.setting.setOnClickListener(this)

        //move favorite activity button
        binding.favorite.setOnClickListener(this)

        //button add/delete favorite
        binding.favoriteButton.setOnClickListener(this)

        //viewModel
        viewModel = ViewModelProvider(this)[DetailActivityViewModel::class.java]
        viewModel.getUser(user.login.toString())

        user.id?.let { viewModel.getFavoriteUserById(it, applicationContext) }
        viewModel.favoriteUser.observe(this, {
            if(it.count >= 1){
                statusFavorite = true
            }
            Log.d("dataku", "data: ${it.count}")
            setStatusFavorite(statusFavorite)
        })


        //bind data
        bindDataUser()

        //progressBar
        showProgressBar()

        //set pager and tab
        setPagerAdapter(user.login)

    }

    private fun setPagerAdapter(username: String?) {
        val pagerAdapter = PagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        val tabs: TabLayout = binding.tabs
        pagerAdapter.username = username
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabs, viewPager){
            tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    private fun showProgressBar() {
        viewModel.progressBar.observe(this, {
            if (it)
                binding.progressBarDetail.visibility = View.VISIBLE
            else
                binding.progressBarDetail.visibility = View.GONE
        })
    }

    private fun bindDataUser() {
        viewModel.userData.observe(this, {
            with(it){
                binding.imageUserDetail.loadImage(avatar_url)
                binding.usernameUser.text = login
                binding.nameUser.text = name
                binding.locationUser.text = location
                binding.companyUser.text = company
                binding.repositoryUser.text = public_repos.toString()
                binding.followerUser.text = followers.toString()
                binding.followingUser.text = following.toString()
            }
        })
    }

    private fun CircleImageView.loadImage(url: String){
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().override(80, 80))
            .centerCrop()
            .into(binding.imageUserDetail)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
            R.id.favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
            R.id.button_back ->{
                onBackPressed()
            }
            R.id.favorite_button -> {
                statusFavorite = !statusFavorite
                setFavorite(statusFavorite)
            }

        }
    }

    private fun setStatusFavorite(status: Boolean) {
        if (status)
            binding.favoriteButton.setImageResource(R.drawable.love_full)
        else
            binding.favoriteButton.setImageResource(R.drawable.love_not_full)
    }

    private fun setFavorite(status: Boolean) {
        if (status) {
            val content = MappingHelper.convertToContentValues(user)
            viewModel.setFavoriteUser(content,applicationContext)
            user.id?.let { viewModel.getFavoriteUserById(it, applicationContext) }
            Toast.makeText(this, "add Favorite", Toast.LENGTH_SHORT).show()

        } else {
            user.id?.let { viewModel.deleteFavoriteUser(it, applicationContext) }
            user.id?.let { viewModel.getFavoriteUserById(it, applicationContext) }
            Toast.makeText(this, "delete Favorite", Toast.LENGTH_SHORT).show()
        }
    }



}