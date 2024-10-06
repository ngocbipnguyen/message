package com.example.message.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    lateinit var viewModel : VM
    lateinit var binding: VB

    protected abstract fun createViewModel(): VM

    protected abstract fun inflateBinding(inflater: LayoutInflater?, container: ViewGroup?): VB

    protected abstract fun initViews()

    protected abstract fun observeVM()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateBinding(layoutInflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = createViewModel()
        initViews()

    }

}