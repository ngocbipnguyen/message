package com.example.message.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.message.base.BaseViewModel
import com.example.message.constants.FirebaseConstants
import com.example.message.source.models.Message
import com.example.message.source.models.User
import com.example.message.ui.notification.PushNotification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(@ApplicationContext val context: Context) : BaseViewModel() {

    private var _messages = MutableLiveData<List<Message>>()
    val messages = _messages

    lateinit var grounp_message: String

    private var userCurrent: String
    private lateinit var token: String

    init {
        userCurrent = FirebaseAuth.getInstance().currentUser?.uid.toString()

        viewModelScope.launch {
            val doc = FirebaseFirestore.getInstance().collection(FirebaseConstants.userPath)
                .document(userCurrent).get().await()

            token = doc.get(FirebaseConstants.pushToken).toString()

        }

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

    suspend fun sendMessage(
        messageText: String,
        typeText: String,
        uid: String,
        displayName: String
    ) {
        val datetime = Date()
        val timestamps: String = datetime.time.toString()

        val messageData: MutableMap<String, Any> = HashMap()

        messageData[FirebaseConstants.idFrom] = userCurrent
        messageData[FirebaseConstants.idTo] = uid
        messageData[FirebaseConstants.timestamp] = timestamps
        messageData[FirebaseConstants.content] = messageText
        messageData[FirebaseConstants.type] = typeText

        val result = FirebaseFirestore.getInstance().collection(FirebaseConstants.pathMessages)
            .document(grounp_message).collection(grounp_message).document(timestamps)
            .set(messageData).await()
        pushNotification(messageText, displayName, token)

    }


    fun pushNotification(messageText: String, title: String, token: String) {

        val client = OkHttpClient()

        val json = JSONObject(message(token, title, messageText)).toString()

        val serverKey = PushNotification.getAccessToken(context)

        val request = Request.Builder()
            .url("https://fcm.googleapis.com/v1/projects/message-b3631/messages:send")
            .addHeader("Authorization", "Bearer $serverKey")
            .addHeader("Content-Type", "application/json")
            .post(json.toRequestBody("application/json".toMediaType()))
            .build()

        val response = client.newCall(request).execute()

    }


    fun message(token: String, title: String, body: String): Map<String, Any> {
        return mapOf(
            "message" to mapOf(
                "token" to token,
                "notification" to mapOf(
                    "title" to title,
                    "body" to body
//                    "uid" to "auth.currentUser?.uid",
//                    "displayName" to "auth.currentUser?.displayName",
//                    "photoUrl" to "auth.currentUser?.photoUrl",
//                    "currentToken" to currentToken
                ),
//                "data" to mapOf(
//                    "title" to auth.currentUser?.uid,
//                    "message" to auth.currentUser?.photoUrl,
////                    "customData" to currentToken
//                    "uid" to auth.currentUser?.uid,
//                    "displayName" to auth.currentUser?.displayName,
//                    "photoUrl" to auth.currentUser?.photoUrl,
//                    "currentToken" to currentToken
//                )
            )
        )
    }


}