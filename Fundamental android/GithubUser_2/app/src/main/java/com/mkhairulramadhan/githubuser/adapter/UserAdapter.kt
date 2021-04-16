package com.mkhairulramadhan.githubuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.githubuser.databinding.UserListBinding
import com.mkhairulramadhan.githubuser.network.model.Items

class UserAdapter(private val context: Context): RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var listUser: List<Items> = ArrayList()

    fun setUserList(list: List<Items>){
        this.listUser = list
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = UserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListViewHolder(private val binding: UserListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Items){
            with(binding){
                Glide.with(itemView.context)
                        .load(user.avatar_url)
                        .apply(RequestOptions().override(80, 80))
                        .into(imageUser)
                usernameUser.text = user.login
                userType.text = user.type
                imageUser.setOnClickListener{ onItemClickCallback?.onItemClicked(user)}
                imageDetail.setOnClickListener{ onItemClickCallback?.onItemClicked(user)}
            }
        }

    }

}
