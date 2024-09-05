package com.example.starwars.retrofit

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getClient(context: Context): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit by lazy {
            val BASE_URL = "https://www.jsonkeeper.com/";
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        return retrofit
    }
}