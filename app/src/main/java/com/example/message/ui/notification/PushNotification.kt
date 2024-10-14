package com.example.message.ui.notification

import android.content.Context
import com.google.auth.oauth2.GoogleCredentials

class PushNotification {

    companion object {

        fun getAccessToken(context: Context): String {

            val googleCredentials =
                GoogleCredentials.fromStream(context.assets.open("message.json"))
                    .createScoped("https://www.googleapis.com/auth/firebase.messaging")

            googleCredentials.refresh()

            return googleCredentials.accessToken.tokenValue
        }

    }
}