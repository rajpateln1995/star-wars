package com.example.starwars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.adapter.PlayersListAdapter
import com.example.starwars.data.PlayerItem
import com.example.starwars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PlayerSelectListener {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val viewModel: MainViewModel by viewModels()
    private lateinit var playerAdapter: PlayersListAdapter
    private val decending = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getPlayersList(this)
        viewModel.getMatchList(this)
        initUI()
    }

    private fun initUI() {

        viewModel.playerList.observe(this) {
            val list = it.sortedWith(
                compareByDescending<PlayerItem> { it.score }
                    .thenByDescending { it.totalScore }
            )
            playerAdapter = PlayersListAdapter(list, this, this)
            binding.rvPlayers.adapter = playerAdapter
            binding.rvPlayers.layoutManager = LinearLayoutManager(this)
        }

    }

    override fun onPlayerSelected(data: PlayerItem) {
        val intent = Intent(this, PlayerDetailActivity:: class.java).apply {
            putExtra("MATCH_LIST",
                viewModel.playerMatchMap.get(data.id)?.let { ArrayList(it) })
        }

        startActivity(intent)
    }
}

interface PlayerSelectListener {
    fun onPlayerSelected(data: PlayerItem)
}
