package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DayTest {
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
    fun test(){
        val day = Day()

        day.addPlayerToVoting(p1)
        day.addPlayerToVoting(p2)
        day.addPlayerToVoting(p3)
        day.addPlayerToVoting(p4)

        assertEquals(day.votingList.indexOf(p1), 0)
        assertEquals(day.votingList.indexOf(p2), 1)
        assertEquals(day.votingList.indexOf(p3), 2)
        assertEquals(day.votingList.indexOf(p4), 3)
    }
}