package com.example.message.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.message.base.BaseFragment
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
    }

    override fun observeVM() {
    }
}