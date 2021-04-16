package com.mkhairulramadhan.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkhairulramadhan.githubuser.databinding.UserListBinding

class UserAdapter(private val listUser: ArrayList<UserModel>): RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserModel)
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
        fun bind(user: UserModel){
            with(binding){
                Glide.with(itemView.context)
                        .load(user.avatar)
                        .apply(RequestOptions().override(80, 80))
                        .into(imageUser)
                usernameUser.text = user.username
                countFollowerUser.text = user.followers.toString()
                countFollowingUser.text = user.following.toString()
                locationUser.text = user.location

                imageUser.setOnClickListener{ onItemClickCallback?.onItemClicked(user)}
                llDetail.setOnClickListener{ onItemClickCallback?.onItemClicked(user)}
            }
        }

    }

}
