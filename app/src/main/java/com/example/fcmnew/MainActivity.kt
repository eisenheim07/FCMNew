package com.example.fcmnew

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.fcmnew.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setListeners()

    }

    private fun initViews() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                token = task.result
                println("TOKEN 1.1 => $token")
                hitAPI(token)
            }
    }

    private fun setListeners() {
        binding.btnSend.setOnClickListener {
            hitAPI(token)
        }
    }

    private fun hitAPI(token: String) {
        val mainObj = JSONObject()
        val msg = JSONObject()
        val notification = JSONObject()
        notification.put("title", "TITLE")
        notification.put("body", "BODY")
        msg.put("token", token)
        msg.put("notification", notification)
        mainObj.put("message", msg)

        Log.e("TAG", "TOKEN 1.2 => $mainObj")

        val request: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST,
            "https://fcm.googleapis.com/v1/projects/fcmnew-f697f/messages:send",
            mainObj,
            Response.Listener { response ->
                Log.e("TOKEN RESPONSE 1.3 => ", response.toString())
            },
            Response.ErrorListener { error ->
                Log.e("TOKEN ERROR 1.3 => ", error.message.toString())
            }) {

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val accessToken = AccessToken.getAccessToken()
                Log.e("TAG", "TOKEN 1.4 => $accessToken")
                val headers = HashMap<String, String>()
                headers["content-type"] = "\"Content-Type\", \"application/json; UTF-8\""
                headers["authorization"] = "Bearer $accessToken"
                return headers
            }
        }
        Volley.newRequestQueue(baseContext).add(request)
    }
}