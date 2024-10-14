package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.message.base.BaseFragment
import com.example.message.databinding.HomeFragmentBinding
import com.example.message.source.models.User
import com.example.message.ui.adapter.UserAdapter
import com.example.message.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>() {
    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    lateinit var adapter: UserAdapter

    lateinit var listUsers : ArrayList<User>

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater!!,container,false)
    }

    override fun initViews() {
        binding.lifecycleOwner = this
        listUsers = ArrayList()
        adapter = UserAdapter(requireActivity(),listUsers, onClickUser = { it ->
            val action = HomeFragmentDirections.actionHomeFragmentToMessageFragment(it)
            binding.root.findNavController().navigate(action)
        })

        binding.userRecycle.adapter = adapter

        viewModel.users.observe(this, Observer { it ->
            listUsers.clear()
            listUsers.addAll(it)
            adapter.notifyDataSetChanged()
        })


    }

    override fun observeVM() {
    }
}