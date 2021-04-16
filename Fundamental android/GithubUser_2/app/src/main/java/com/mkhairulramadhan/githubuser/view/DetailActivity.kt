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
import com.mkhairulramadhan.githubuser.db.entities.UserEntity
import com.mkhairulramadhan.githubuser.network.model.Items
import com.mkhairulramadhan.githubuser.viewModel.DetailActivityViewModel
import com.mkhairulramadhan.githubuser.viewModel.FavoriteActivityViewModel
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
    private lateinit var favoriteViewModel: FavoriteActivityViewModel
    private var checkSaveFavorite: Boolean = false
    private lateinit var user :Items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<Items>(USER_EXTRA)!!

        //button back
        binding.buttonBack.setOnClickListener(this)

        //button change language
        binding.language.setOnClickListener(this)

        //button favorite
        binding.favoriteButton.setOnClickListener(this)

        //viewModel
        viewModel = ViewModelProvider(this)[DetailActivityViewModel::class.java]
        viewModel.getUser(user.login)

        //favorite viewmodel
        favoriteViewModel = ViewModelProvider(this).get(FavoriteActivityViewModel::class.java)
        favoriteViewModel.readAllUser.observe(this,{
            Log.d("dataku", it.toString())
        })
        favoriteViewModel.getUserDetail("MKhairulRamadhan")
        favoriteViewModel.getUserByUsername().observe(this, {
            Log.d("dataku", "data detail-: ${it.toString()}")
        })

        //bind data
        bindDataUser()

        //progressBar
        showProgressBar()

        //set pager and tab
        setPagerAdapter(user.login)

    }

    private fun setPagerAdapter(username: String) {
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
            R.id.language -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }

            R.id.button_back ->{
                onBackPressed()
            }

            R.id.favorite_button -> {
//                if (checkSaveFavorite){
                    insertData()
//                }else{
//                    deleteData(user.login)
//                }
            }

        }
    }

    private fun deleteData(username: String) {
        favoriteViewModel.deleteUser(username)
    }

    private fun insertData() {
        val userData = UserEntity(
                id = 1,
                avatar_url = "hitsi",
                login = "MKhairulRamadhan",
                type = "user"
        )

        favoriteViewModel.addUser(userData)
        Toast.makeText(this, "Success add data", Toast.LENGTH_SHORT).show()

    }

}