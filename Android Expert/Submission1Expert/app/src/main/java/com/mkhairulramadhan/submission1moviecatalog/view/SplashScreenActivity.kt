package com.mkhairulramadhan.submission1moviecatalog.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadAnimationSplash()
        startSplash()
    }

    private fun startSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3000)
    }

    private fun loadAnimationSplash() {
        //Animation
        val animationTop: Animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)

        binding.topImage.animation = animationTop
        binding.bottomImage.animation = animationTop

        val objectAnimatiorTop = ObjectAnimator.ofPropertyValuesHolder(
            binding.topImage,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        val objectAnimatiorBottom = ObjectAnimator.ofPropertyValuesHolder(
            binding.bottomImage,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )

        objectAnimatiorTop.duration = 700
        objectAnimatiorTop.repeatCount = ValueAnimator.INFINITE
        objectAnimatiorTop.repeatMode = ValueAnimator.REVERSE
        objectAnimatiorTop.start()

        objectAnimatiorBottom.duration = 700
        objectAnimatiorBottom.repeatCount = ValueAnimator.INFINITE
        objectAnimatiorBottom.repeatMode = ValueAnimator.REVERSE
        objectAnimatiorBottom.start()
    }
}