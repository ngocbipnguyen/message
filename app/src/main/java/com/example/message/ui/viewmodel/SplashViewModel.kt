package com.example.message.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.message.base.BaseViewModel
import com.example.message.source.repository.MessageRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel  @Inject constructor(messageRepository: MessageRepository) : BaseViewModel(){

    private var _isSigning = MutableLiveData<Boolean>()
    val isSigning : LiveData<Boolean> = _isSigning

    init {
        viewModelScope.launch {
            delay(3000)
            _isSigning.value = FirebaseAuth.getInstance().currentUser != null
        }
    }

}