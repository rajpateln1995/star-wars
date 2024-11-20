package com.example.starwars.data

import android.os.Parcel

import java.io.Serializable

data class MatchItem(
    val match: Long,
    val playerName1: String,
    val playerName2: String,
    val score1: String,
    val score2: String
): Serializable {

    override fun toString(): String {
        return "MatchItem(playerName1='$playerName1', playerName2='$playerName2', score1='$score1', score2='$score2')"
    }
}