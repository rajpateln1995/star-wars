package com.example.starwars.retrofit.data

class MatchResponse(val matches: ArrayList<Match>) {
    override fun toString(): String {
        return "MatchResponse(matches=$matches)"
    }
}

class Match(
    var match: Long,
    var player1: PlayerScore,
    var player2: PlayerScore
) {
    override fun toString(): String {
        return "Match(match=$match, player1=$player1, player2=$player2)"
    }
}

class PlayerScore(
    var id: Long,
    val score: Long) {
    override fun toString(): String {
        return "PlayerScore(id=$id, score=$score)"
    }
}