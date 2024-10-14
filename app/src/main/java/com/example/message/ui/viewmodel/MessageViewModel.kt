package com.example.message.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.message.base.BaseViewModel
import com.example.message.constants.FirebaseConstants
import com.example.message.source.models.Message
import com.example.message.source.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor() : BaseViewModel() {

    private var _messages = MutableLiveData<List<Message>>()
    val messages = _messages

    lateinit var grounp_message: String

    private var userCurrent: String

    init {
        userCurrent = FirebaseAuth.getInstance().currentUser?.uid.toString()
    }


    suspend fun setGroupMessage(user: User) {
        grounp_message = if (user.uid > userCurrent) {
            "${user.uid}-${userCurrent}"
        } else {
            "${userCurrent}-${user.uid}"
        }


        val docs = FirebaseFirestore.getInstance().collection(FirebaseConstants.pathMessages)
            .document(grounp_message).collection(grounp_message).limit(30)
            .orderBy(FirebaseConstants.timestamp).get().await()

        val list = ArrayList<Message>()

        docs.documents

        docs.documents.forEach { doc ->
            val message = Message(
                doc.data?.get("idFrom").toString(),
                doc.data?.get("idTo").toString(),
                doc.data?.get("timestamp").toString(),
                doc.data?.get("content").toString(),
                doc.data?.get("type").toString()
            )

            list.add(message)
        }

        _messages.postValue(list)


    }

}