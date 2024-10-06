package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.message.base.BaseFragment
import com.example.message.databinding.HomeFragmentBinding
import com.example.message.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>() {
    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater!!,container,false)
    }

    override fun initViews() {
    }

    override fun observeVM() {
    }
}