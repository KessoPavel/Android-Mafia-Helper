package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class VotingTest {
    lateinit var voting: Voting
    lateinit var playerList: ArrayList<Player>
    val p1 = Player(name = "1")
    val p2 = Player(name = "2")
    val p3 = Player(name = "3")
    val p4 = Player(name = "4")

    @Before
    fun setUp() {
        playerList = ArrayList()
        playerList.add(p1)
        playerList.add(p2)
        playerList.add(p3)
        playerList.add(p4)
    }

    @Test
    fun test1(){
        voting = Voting(playerList)

        var votingCount = 0

        while (true) {
            val player = voting.nextPlayer() ?: break

            if (player == p1)
                voting.votingResault(3)
            else if (player == p2)
                voting.votingResault(4)
            else if (player == p3)
                voting.votingResault(0)
            else if (player == p4){
                voting.votingResault(2)
            }

            votingCount++
        }


        assertEquals(votingCount, playerList.size)
        assertEquals(voting.endVoting().size, 1)
        assertEquals(voting.endVoting()[0], p2)
    }

    @Test
    fun test2(){
        voting = Voting(playerList)

        var votingCount = 0

        while (true) {
            val player = voting.nextPlayer() ?: break

            if (player == p1)
                voting.votingResault(3)
            else if (player == p2)
                voting.votingResault(4)
            else if (player == p3)
                voting.votingResault(4)
            else if (player == p4){
                voting.votingResault(2)
            }

            votingCount++
        }


        assertEquals(votingCount, playerList.size)
        assertEquals(voting.endVoting().size, 2)
        assertTrue(voting.endVoting().contains(p2))
        assertTrue(voting.endVoting().contains(p3))
    }
}