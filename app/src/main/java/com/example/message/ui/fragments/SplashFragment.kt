package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.message.base.BaseFragment
import com.example.message.databinding.SplashFragmentBinding
import com.example.message.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, SplashFragmentBinding>() {
    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(requireActivity()).get(SplashViewModel::class.java)
    }

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): SplashFragmentBinding {
       return  SplashFragmentBinding.inflate(inflater!!,container, false)
    }

    override fun initViews() {
    }

    override fun observeVM() {
    }
}