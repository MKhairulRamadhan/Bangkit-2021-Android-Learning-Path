package com.mkhairulramadhan.githubuser.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mkhairulramadhan.githubuser.view.fragment.FollowersFragment
import com.mkhairulramadhan.githubuser.view.fragment.FollowingFragment

class PagerAdapter(
    activity: AppCompatActivity
): FragmentStateAdapter(activity)
{
    var username: String? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment().newInstance(username.toString())
            1 -> fragment = FollowingFragment().newInstance(username.toString())
        }
        return fragment as Fragment
    }

}