package com.example.starwars.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwars.PlayerSelectListener
import com.example.starwars.R
import com.example.starwars.data.PlayerItem
import com.example.starwars.databinding.PlayerItemViewBinding

class PlayersListAdapter(var playerList: List<PlayerItem>, var context: Context, val listener: PlayerSelectListener) :
    RecyclerView.Adapter<PlayersListAdapter.PlayerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PlayerItemViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.player_item_view, parent, false)
        return PlayerItemViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerItemViewHolder, position: Int) {
        holder.bind(playerList.get(position))
    }

    inner class PlayerItemViewHolder(val binding: PlayerItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PlayerItem) {
            binding.clPlayer.setOnClickListener {
                listener.onPlayerSelected(data)
            }
            binding.tvPlayerName.text = data.PlayerName
            binding.tvPlayerScore.text = data.score.toString()
            Glide.with(context).load(data.iconUrl.replace("http", "https")).into(binding.ivPlayer)
        }
    }
}