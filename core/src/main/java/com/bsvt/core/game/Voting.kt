package com.bsvt.core.game

import com.bsvt.core.character.Character


class Voting(private val votingList: ArrayList<Character>) {
    val isLastPlayer: Boolean
        get() = this.currentPlayerIndex == votingList.size - 1

    val votingMap: HashMap<Character, Int> = HashMap()
    private var currentPlayerIndex = -1

    fun nextPlayer(): Character?{
        currentPlayerIndex++
        if (currentPlayerIndex == votingList.size)
            return null

        return votingList[currentPlayerIndex]
    }

    fun votingResault(result: Int){
        votingMap[votingList[currentPlayerIndex]] = result
    }

    fun endVoting(): ArrayList<Character> {
        var max = 0
        val answer = ArrayList<Character>()

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