package com.example.message.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.message.base.BaseViewModel
import com.example.message.constants.FirebaseConstants
import com.example.message.source.models.User
import com.example.message.source.repository.MessageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext val context: Context,messageRepository: MessageRepository) : BaseViewModel() {
    private var _users = MutableLiveData<List<User>>()
    val users = _users


    init {

        viewModelScope.launch {
            getUsers()

            val token = FirebaseMessaging.getInstance().token.await()


            val mapUser: MutableMap<String, Any> = HashMap()
            mapUser[FirebaseConstants.pushToken] = token

            FirebaseFirestore.getInstance().collection(FirebaseConstants.userPath).document(
                FirebaseAuth.getInstance().currentUser?.uid!!
            ).update(mapUser).await()
        }



    }

    suspend fun getUsers() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val userDocs = FirebaseFirestore.getInstance().collection(FirebaseConstants.userPath).get().await()
        if (!userDocs.isEmpty) {
            var usersList = ArrayList<User>()
            userDocs.documents.forEach { it ->
                val uid = it.id
                val name = it.get(FirebaseConstants.displayName).toString()
                val email = it.get(FirebaseConstants.email).toString()
                val photo = it.get(FirebaseConstants.photoUrl).toString()
                val token = it.get(FirebaseConstants.pushToken).toString()
                usersList.add(User(uid, name,email,photo, token))
            }
            _users.postValue(usersList)
        }
    }


}