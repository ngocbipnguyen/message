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
import com.example.message.constants.Constants
import com.example.message.source.models.Message
import com.example.message.source.models.User
import de.hdodenhof.circleimageview.CircleImageView

class MessageAdapter(val context: Context, val messages: ArrayList<Message>,val user: User) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

    class RightMessage(val view: View): ViewHolder(view) {

        private val messageText: TextView
        private val photo: ImageView

        init {
            messageText = view.findViewById(R.id.text_right)
            photo = view.findViewById(R.id.photo_right)
        }

        fun bind(message: Message) {
            if (message.type == Constants.TYPE_TEXT) {
                messageText.visibility = View.VISIBLE
                photo.visibility = View.GONE
                messageText.text = message.content
            } else {
                messageText.visibility = View.GONE
                photo.visibility = View.VISIBLE
//                messageText.text = message.content

                Glide.with(view).load(message.content).into(photo)
            }

        }


    }


    class LeftMessage(val view: View,val user: User): ViewHolder(view) {
        private val messageText: TextView
        private val photo: ImageView
        private val circleImgae: CircleImageView

        init {
            messageText = view.findViewById(R.id.text_left)
            photo = view.findViewById(R.id.photo_left)
            circleImgae = view.findViewById(R.id.circle_image)
        }

        fun bind(message: Message) {
            Glide.with(view).load(user.photoUrl).into(circleImgae)
            if (message.type == Constants.TYPE_TEXT) {
                messageText.visibility = View.VISIBLE
                photo.visibility = View.GONE
                messageText.text = message.content
            } else {
                messageText.visibility = View.GONE
                photo.visibility = View.VISIBLE

                Glide.with(view).load(message.content).into(photo)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
       return if (viewType == Constants.LEFT_MESSAGE) {
           val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.left_message, parent, false)
            LeftMessage(view,user)
        } else {
           val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.right_message, parent, false)
           RightMessage(view)
        }
    }

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {

        if (messages[position].idFrom.equals(user.uid)) {
            val rightHolder: RightMessage = holder as RightMessage

            rightHolder.bind(messages[position])
        } else {
            val rightHolder: LeftMessage = holder as LeftMessage

            rightHolder.bind(messages[position])
        }

    }

    override fun getItemCount(): Int  = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].idFrom.equals(user.uid)) {
            Constants.LEFT_MESSAGE
        } else {
            Constants.RIGHT_MESSAGE
        }
    }
}