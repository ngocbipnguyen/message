package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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

        binding.lifecycleOwner = this
        viewModel.isSigning.observe(viewLifecycleOwner,  Observer<Boolean> { isSigning ->
            // Update the UI, in this case, a TextView.
            if (isSigning) {
//                binding.root.findNavController().navigate(action_splashFragment_to_homeFragment)
            } else {

            }
        })
    }

    override fun observeVM() {
    }
}