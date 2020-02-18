package com.bsvt.core.game

import com.bsvt.core.character.Character

class Day {
    var votingList: ArrayList<Character> = ArrayList()

    fun addPlayerToVoting(player: Character){
        votingList.add(player)
    }

    fun addAll(players: ArrayList<Character>){
        votingList.addAll(players)
    }
}