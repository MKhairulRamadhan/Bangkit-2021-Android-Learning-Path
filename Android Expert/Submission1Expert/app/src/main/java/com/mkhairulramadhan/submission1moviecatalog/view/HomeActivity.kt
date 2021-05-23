package com.mkhairulramadhan.submission1moviecatalog.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mkhairulramadhan.submission1moviecatalog.R
import com.mkhairulramadhan.submission1moviecatalog.databinding.ActivityMainBinding
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.MoviesFragment
import com.mkhairulramadhan.submission1moviecatalog.view.fragment.TvShowFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnMain.setOnNavigationItemSelectedListener(navigationSelector)
        if(savedInstanceState == null){
            val fragment = MoviesFragment.newInstance()
            addFragment(fragment)
        }
    }

    private val navigationSelector = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_movies -> {
                val fragment = MoviesFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tv -> {
                val fragment = TvShowFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_fav -> {
                goFavoriteFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.fl_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun goFavoriteFragment() {
        val fragment = instantiateFavoriteFragment(namePath)
        if (fragment != null) {
            addFragment(fragment)
        }
    }

    private fun instantiateFavoriteFragment(className: String): Fragment? {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private val namePath: String
        get() = "com.mkhairulramadhan.favorite.view.FavoriteFragment"

}