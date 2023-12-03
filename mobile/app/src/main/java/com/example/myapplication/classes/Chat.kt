package com.example.myapplication.classes

import android.util.Log
import com.example.myapplication.retrofit.ChatModel
import com.example.myapplication.retrofit.RetrofitInit

class Chat {

    suspend fun getAllUserChat(
        retrofitInit: RetrofitInit,
        baseURL: String,
        tokenManager: TokenManager
    ): List<ChatModel>?{
        retrofitInit.initRetrofit(baseURL)
        try {
            val tokensPair = tokenManager.retrieveTokens()
            val accessToken = "Bearer " + tokensPair.first
            val refreshToken = "Bearer " + tokensPair.second
            if (accessToken.isEmpty() || refreshToken.isEmpty()) {
                return null
            }
            return retrofitInit.mainApi.getChats(accessToken)
        } catch (e: Exception) {
            Log.e("Error while getting chats", e.toString())
            return null
        }

    }

}