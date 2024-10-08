package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.message.base.BaseFragment
import com.example.message.constants.Constants
import com.example.message.databinding.LoginFragmentBinding
import com.example.message.ui.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<LoginViewModel, LoginFragmentBinding>() {
    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun inflateBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): LoginFragmentBinding {
        return LoginFragmentBinding.inflate(inflater!!,container,false)
    }

    override fun initViews() {

        binding.googleIcon.setOnClickListener {
            viewModel.sign(Constants.GOOGLE_SIGN, doOnSuccess = {

                val action =
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()

                binding.root.findNavController().navigate(action)
            }, doOnFailure = {
                Toast.makeText(requireContext(),"Login is failure", Toast.LENGTH_SHORT).show()
            })
        }

    }

    override fun observeVM() {
    }
}