package com.mkhairulramadhan.githubuser.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkhairulramadhan.githubuser.adapter.FollowAdapter
import com.mkhairulramadhan.githubuser.databinding.FragmentFollowersBinding
import com.mkhairulramadhan.githubuser.viewModel.FollowersFragmentViewModel

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersFragmentViewModel

    companion object{
        private const val ARG_USERNAME = "username"
    }

    fun newInstance(username: String): FollowersFragment {
        val fragment = FollowersFragment()
        val bundle = Bundle()
        bundle.putString(ARG_USERNAME, username)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //viewModel
        viewModel = ViewModelProvider(this)[FollowersFragmentViewModel::class.java]
        viewModel.getFollowers(arguments?.getString(ARG_USERNAME).toString())

        //recyclerView
        binding.rvUserFollowers.setHasFixedSize(true)
        showRecyclerViewList()

        //progressBar
        showProgressBar()
    }

    private fun showProgressBar() {
        viewModel.progressBar.observe(activity!!, {
            if (it)
                binding.progressBarFollowers.visibility = View.VISIBLE
            else
                binding.progressBarFollowers.visibility = View.GONE
        })
    }

    private fun showRecyclerViewList() {
        binding.rvUserFollowers.layoutManager = LinearLayoutManager(activity)
        val userAdapter = FollowAdapter(activity!!)
        viewModel.listFollowers.observe(activity!!, {
            userAdapter.setUserList(it)
            setNotFoundImage(it.isEmpty())
        })
        binding.rvUserFollowers.adapter = userAdapter
    }

    private fun setNotFoundImage(status: Boolean){
        binding.rvUserFollowers.visibility = if (status) View.GONE else View.VISIBLE
        binding.imageNotFound.visibility = if (status) View.VISIBLE else View.GONE
        binding.textNotFound1.visibility = if (status) View.VISIBLE else View.GONE
        binding.textNotFound2.visibility = if (status) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}