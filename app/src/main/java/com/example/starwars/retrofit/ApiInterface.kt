package com.example.starwars.retrofit

import com.example.starwars.retrofit.Endpoints.MATCH_ENDPOINT
import com.example.starwars.retrofit.Endpoints.PLAYERS_ENDPOINT
import com.example.starwars.retrofit.data.Match
import com.example.starwars.retrofit.data.MatchResponse
import com.example.starwars.retrofit.data.Player
import com.example.starwars.retrofit.data.PlayerListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET(PLAYERS_ENDPOINT)
    suspend fun getPlayersList(): Response<List<Player>>

    @GET(MATCH_ENDPOINT)
    suspend fun getMatchList(): Response<List<Match>
            >
}