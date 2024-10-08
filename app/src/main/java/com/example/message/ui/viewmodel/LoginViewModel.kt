package com.example.message.ui.viewmodel

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.viewModelScope
import com.example.message.R
import com.example.message.base.BaseViewModel
import com.example.message.constants.FirebaseConstants
import com.example.message.source.repository.MessageRepository
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@ApplicationContext val context: Context,messageRepository: MessageRepository) : BaseViewModel() {

    init {


    }


    fun sign(typeSigning: Int, doOnSuccess: (Boolean) -> Unit,  doOnFailure: (Boolean) -> Unit) {
        viewModelScope.launch {
            signGoogle(doOnSuccess, doOnFailure)
        }
    }



    suspend fun signGoogle(doOnSuccess: (Boolean) -> Unit,  doOnFailure: (Boolean) -> Unit) {
//
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(context.getString(R.string.WEB_CLIENT_ID))
            .setAutoSelectEnabled(true)
        .build()
//

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credentialManager = CredentialManager.create(context)

        val result = credentialManager.getCredential(
            context = context,
            request = request
        )

        val  credential = result.credential

        val googleToken = GoogleIdTokenCredential.createFrom(credential.data)

        val authCredential = GoogleAuthProvider.getCredential(googleToken.idToken, null)

        val data = FirebaseAuth.getInstance().signInWithCredential(authCredential).await()

        data.user?.let {

            val user = hashMapOf(
                FirebaseConstants.displayName to it.displayName,
                FirebaseConstants.email to  it.email,
                FirebaseConstants.photoUrl to it.photoUrl,
                FirebaseConstants.pushToken to ""
            )

            val docs =  FirebaseFirestore.getInstance().collection(FirebaseConstants.userPath).document(it.uid).get().await()

            if(!docs.exists()) {
                FirebaseFirestore.getInstance().collection(FirebaseConstants.userPath).document(it.uid).set(user).addOnSuccessListener {
                    doOnSuccess(true)
                }.addOnFailureListener {
                    doOnSuccess(false)
                }
            } else {
                doOnSuccess(true)
            }
        }

    }

}