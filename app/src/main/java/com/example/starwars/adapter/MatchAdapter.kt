package com.example.starwars.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.data.MatchItem
import com.example.starwars.databinding.MatchItemViewBinding

class MatchAdapter(var matchList: List<MatchItem>, var context: Context) :
    RecyclerView.Adapter<MatchAdapter.MatchItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: MatchItemViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.match_item_view, parent, false)
        return MatchItemViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchItemViewHolder, position: Int) {
        holder.bind(matchList.get(position))
    }

    inner class MatchItemViewHolder(val binding: MatchItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MatchItem) {
            binding.tvPlayer1.text = data.playerName1
            binding.tvPlayer2.text = data.playerName2
            binding.tvScore.text = context.getString(R.string.score, data.score1, data.score2)
            if ()
        }
    }
}