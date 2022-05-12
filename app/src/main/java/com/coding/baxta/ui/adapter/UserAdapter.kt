package com.coding.baxta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coding.baxta.databinding.LiUserBinding
import com.coding.baxta.local.user.entity.User

class UserAdapter : ListAdapter<User, UserAdapter.VH>(UserItemCallback) {

    var clickListener : (User) -> Unit = {}

    inner class VH(private val binder: LiUserBinding) : RecyclerView.ViewHolder(binder.root) {
        fun bind(user: User) {
            binder.fullName.text = user.fullName
            binder.userName.text = user.userName
            binder.followerCount.text = user.followersCount.toString()
            binder.root.setOnClickListener {
                clickListener(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LiUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}

object UserItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.email == newItem.email &&
                oldItem.dateOfBirth == newItem.dateOfBirth
    }

}