package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player

class Voting(private val votingList: ArrayList<Player>) {
    private val votingMap: HashMap<Player, Int> = HashMap()
    private var currentPlayerIndex = -1

    fun nextPlayer(): Player?{
        currentPlayerIndex++
        if (currentPlayerIndex == votingList.size)
            return null

        return votingList[currentPlayerIndex]
    }

    fun votingResault(result: Int){
        votingMap[votingList[currentPlayerIndex]] = result
    }

    fun endVoting(): ArrayList<Player> {
        var max = 0
        val answer = ArrayList<Player>()

        for (key in votingMap.keys){
            val cur = votingMap[key]

            if (cur == max){
                answer.add(key)
            } else if (cur != null) {
                if (cur > max) {
                    max = cur
                    answer.clear()
                    answer.add(key)
                }
            }
        }

        return answer
    }
}