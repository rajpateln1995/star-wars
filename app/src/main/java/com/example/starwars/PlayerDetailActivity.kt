package com.example.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.adapter.MatchAdapter
import com.example.starwars.adapter.PlayersListAdapter
import com.example.starwars.data.MatchItem
import com.example.starwars.databinding.ActivityMainBinding
import com.example.starwars.databinding.ActivityPlayerDetailBinding

class PlayerDetailActivity : AppCompatActivity() {

    val binding: ActivityPlayerDetailBinding by lazy {
        ActivityPlayerDetailBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
        val matchList: ArrayList<MatchItem>? = intent.getSerializableExtra("MATCH_LIST") as? ArrayList<MatchItem>
        matchAdapter = matchList?.let { MatchAdapter(it, this) }!!
        binding.rvMatches.adapter = matchAdapter
        binding.rvMatches.layoutManager = LinearLayoutManager(this)
    }
}