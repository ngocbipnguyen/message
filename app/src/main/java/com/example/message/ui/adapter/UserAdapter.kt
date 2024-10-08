package com.example.message.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.message.R
import com.example.message.source.models.User

class UserAdapter(val context: Context, val users: List<User>?,val onClickUser:(User) -> Unit) : RecyclerView.Adapter<UserAdapter.Holder>() {

    class Holder(val view: View,val onClickUser:(User) -> Unit) : RecyclerView.ViewHolder(view) {

        var nameText: TextView
        var email: TextView
        var image: ImageView

        init {
            nameText = view.findViewById(R.id.name_user)
            email = view.findViewById(R.id.email)
            image = view.findViewById(R.id.user_photo)

        }

        fun bind(user: User) {
            nameText.text = user.displayName
            email.text = user.email
            Glide.with(view).load(user.photoUrl).into(image)

            view.setOnClickListener {
                onClickUser(user)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent,false)
        return Holder(view,onClickUser )
    }

    override fun onBindViewHolder(holder: UserAdapter.Holder, position: Int) {
        holder.bind(users!!.get(position))
    }

    override fun getItemCount(): Int = users!!.size
}