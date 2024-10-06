package com.example.message.ui.viewmodel

import com.example.message.base.BaseViewModel
import com.example.message.source.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(messageRepository: MessageRepository) : BaseViewModel() {
}