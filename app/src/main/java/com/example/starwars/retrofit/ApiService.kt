package com.example.starwars.retrofit

import android.content.Context
import com.example.starwars.retrofit.Endpoints.MATCH_ENDPOINT
import com.example.starwars.retrofit.Endpoints.PLAYERS_ENDPOINT
import com.example.starwars.retrofit.data.Match
import com.example.starwars.retrofit.data.MatchResponse
import com.example.starwars.retrofit.data.Player
import com.example.starwars.retrofit.data.PlayerListResponse
import kotlinx.coroutines.flow.Flow

class ApiService(private val context: Context) : BaseApiService(context) {

    private val apiClient by lazy { ApiClient.getClient(context).create(ApiInterface::class.java) }

    fun getPlayerList(): Flow<ApiResponse<List<Player>>> {
        return handleApiCall (
            apiCall = { apiClient.getPlayersList() },
            endpoint = PLAYERS_ENDPOINT
        )
    }


    fun getMatchList(): Flow<ApiResponse<List<Match>>> {
        return handleApiCall (
            apiCall = { apiClient.getMatchList() },
            endpoint = MATCH_ENDPOINT
        )
    }
    companion object {
        private const val TAG = "ApiService"

    }

}