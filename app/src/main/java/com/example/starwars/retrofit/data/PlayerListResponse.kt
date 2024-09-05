package com.example.starwars.retrofit.data

data class PlayerListResponse(
    var players: ArrayList<Player>
) {
    override fun toString(): String {
        return "PlayerListResponse(players=$players)"
    }
}

data class Player(
    var id: Long,
    var name: String,
    var icon: String
) {
    override fun toString(): String {
        return "Player(id=$id, name='$name', icon='$icon')"
    }
}