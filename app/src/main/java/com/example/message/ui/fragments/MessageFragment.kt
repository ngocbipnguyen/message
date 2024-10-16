package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.message.base.BaseFragment
import com.example.message.constants.Constants
import com.example.message.databinding.MessageFragmentBinding
import com.example.message.source.models.Message
import com.example.message.source.models.User
import com.example.message.ui.adapter.MessageAdapter
import com.example.message.ui.viewmodel.MessageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessageFragment : BaseFragment<MessageViewModel, MessageFragmentBinding>() {

    lateinit var user: User

    lateinit var adapter: MessageAdapter

    lateinit var messages: ArrayList<Message>

    override fun createViewModel(): MessageViewModel {
        return ViewModelProvider(requireActivity())[MessageViewModel::class.java]

    }

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): MessageFragmentBinding {
        return  MessageFragmentBinding.inflate(inflater!!,container, false)
    }

    override fun initViews() {

        user = MessageFragmentArgs.fromBundle(requireArguments()).users!!
        binding.toolbarMessage.title = user.displayName

        messages = ArrayList<Message>()
        adapter = MessageAdapter(requireContext(), messages, user)
        binding.messageRecycle.adapter = adapter

        binding.lifecycleOwner = this

        viewModel.messages.observe(this, Observer { it ->
            messages.clear()
            messages.addAll(it)
            adapter.notifyDataSetChanged()
        })


        binding.sendIcon.setOnClickListener {
            val messageText = binding.messageEdit.text.toString().trim()
            if (messageText.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.Default) {
                    viewModel.sendMessage(messageText,Constants.TYPE_TEXT, user.uid, user.displayName)
                }
            }
        }

        binding.cameraIcon.setOnClickListener {

        }

        binding.photoIcon.setOnClickListener {

        }


    }

    override fun observeVM() {
    }
}