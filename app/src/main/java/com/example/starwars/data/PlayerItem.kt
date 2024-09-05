package com.example.starwars.data

data class PlayerItem(
    var id: Long,
    var iconUrl: String,
    var PlayerName: String,
    var score: Long,
    var totalScore: Long
) {
    override fun toString(): String {
        return "PlayerItem(iconUrl='$iconUrl', PlayerName='$PlayerName', score=$score)"
    }
}