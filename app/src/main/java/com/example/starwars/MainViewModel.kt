package com.example.starwars

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.MatchItem
import com.example.starwars.data.PlayerItem
import com.example.starwars.retrofit.ApiResponse
import com.example.starwars.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    val playerList: MutableLiveData<ArrayList<PlayerItem>> = MutableLiveData(ArrayList())
    val playerMap: HashMap<Long, PlayerItem> = HashMap()
    val playerMatchMap: HashMap<Long, ArrayList<MatchItem>> = HashMap()

    fun getPlayersList(context: Context) {

        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                ApiService(context).getPlayerList().collect {
                    when (it) {
                        is ApiResponse.Success -> {
                            withContext(Dispatchers.Main) {
                                it.data.forEach { player ->
                                    playerMap.put(player.id, PlayerItem(player.id ,player.icon, player.name, 0, 0))
                                    playerMatchMap.put(player.id, ArrayList())
                                }
                                getMatchList(context)
                            }
                        }

                        is ApiResponse.Error -> {
                            Log.i(TAG, it.toString())
                        }

                        is ApiResponse.Loading -> {
                            Log.i(TAG, it.toString())
                        }
                    }
                }
            }
        }
    }

    fun getMatchList(context: Context) {

        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                ApiService(context).getMatchList().collect {
                    when (it) {
                        is ApiResponse.Success -> {
                            withContext(Dispatchers.Main) {
                                it.data.forEach { match ->
                                    if(match.player1.score > match.player2.score) {
                                        playerMap.get(match.player1.id)?.let {
                                            it.score += 3
                                        }
                                    } else if (match.player1.score == match.player2.score) {
                                        playerMap.get(match.player2.id)?.let {
                                            it.score += 1
                                        }
                                        playerMap.get(match.player1.id)?.let {
                                            it.score += 1
                                        }
                                    } else {
                                        playerMap.get(match.player2.id)?.let {
                                            it.score += 3
                                        }
                                    }

                                    match.let { m ->
                                        val p1_id = m.player1.id
                                        val p2_id = m.player2.id

                                        val p1_name = playerMap.get(p1_id)?.PlayerName
                                        val p2_name = playerMap.get(p2_id)?.PlayerName

                                        val p1_score = m.player1.score
                                        val p2_score = m.player2.score

                                        playerMap.get(p1_id)?.let {
                                            it.totalScore += p1_score
                                        }
                                        playerMap.get(p2_id)?.let {
                                            it.totalScore += p2_score
                                        }

                                        playerMatchMap.get(p1_id)?.add(MatchItem(m.match, p1_name!!, p2_name!!, p1_score.toString(), p2_score.toString()))
                                        playerMatchMap.get(p2_id)?.add(MatchItem(m.match, p2_name!!, p1_name!!, p2_score.toString(), p1_score.toString()))
                                    }
                                }
                                val list = ArrayList(playerMap.values)
                                val finalList = list.sortedWith(
                                    compareByDescending<PlayerItem> { it.score }
                                        .thenByDescending { it.totalScore }
                                )
                                playerList.value = ArrayList(finalList)
                            }
                        }

                        is ApiResponse.Error -> {
                            Log.i(TAG, it.toString())
                        }

                        is ApiResponse.Loading -> {
                            Log.i(TAG, it.toString())
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}