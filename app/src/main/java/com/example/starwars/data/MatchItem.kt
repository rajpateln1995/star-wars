package com.example.starwars.data

import android.os.Parcel

import java.io.Serializable

data class MatchItem(
    var playerName1: String,
    var playerName2: String,
    var score1: String,
    var score2: String
): Serializable {

    override fun toString(): String {
        return "MatchItem(playerName1='$playerName1', playerName2='$playerName2', score1='$score1', score2='$score2')"
    }
}