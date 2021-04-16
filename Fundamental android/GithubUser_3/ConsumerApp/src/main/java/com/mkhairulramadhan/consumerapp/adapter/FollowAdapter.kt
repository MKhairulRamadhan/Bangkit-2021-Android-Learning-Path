package com.mkhairulramadhan.consumerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.consumerapp.databinding.FollowListBinding
import com.mkhairulramadhan.consumerapp.db.network.model.Items

class FollowAdapter(private val context: Context): RecyclerView.Adapter<FollowAdapter.ListViewHolder>() {

    private var listUser: List<Items> = ArrayList()

    fun setUserList(list: List<Items>){
        this.listUser = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = FollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListViewHolder(private val binding: FollowListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Items){
            with(binding){
                Glide.with(itemView.context)
                        .load(user.avatar_url)
                        .apply(RequestOptions().override(80, 80))
                        .into(imageUser)
                usernameUser.text = user.login
                userType.text = user.type
            }
        }

    }

}
