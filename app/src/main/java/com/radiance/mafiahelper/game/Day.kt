package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player

class Day {
    var votingList: ArrayList<Player> = ArrayList()

    fun addPlayerToVoting(player: Player){
        votingList.add(player)
    }

    fun addAll(players: ArrayList<Player>){
        votingList.addAll(players)
    }
}