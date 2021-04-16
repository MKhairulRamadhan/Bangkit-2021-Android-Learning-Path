package com.mkhairulramadhan.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object{
        const val USER_EXTRA = "user_extra"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<UserModel>(USER_EXTRA) as UserModel
        bindDataUser(user)

        binding.buttonBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun bindDataUser(user: UserModel) {
        Glide.with(this)
                .load(user.avatar)
                .apply(RequestOptions().override(80, 80))
                .into(binding.imageUserDetail)
        binding.usernameUser.text = user.username
        binding.nameUser.text = user.name
        binding.locationUser.text = user.location
        binding.companyUser.text = user.company
        binding.repositoryUser.text = user.repository
        binding.followerUser.text = user.followers
        binding.followingUser.text = user.following
    }

}