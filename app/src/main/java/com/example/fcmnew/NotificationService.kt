package com.example.fcmnew

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class NotificationService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            Log.e("TAG", "TOKEN TITLE => ${it.title}")
            Log.e("TAG", "TOKEN MESSAGE => ${it.body}")
        }
        super.onMessageReceived(message)
    }
}