package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role
import java.io.Serializable

class Game(val players: ArrayList<Player>) : Serializable {
    val playersCont: Int
        get() = players.size

    var gameOptions: GameOptions = GameOptions()
    var day: Day? = null
        set(value) {
            value?.let {
                dayList.add(it)
            }
            field = value
        }
    var voting: Voting? = null
        set(value) {
            value?.let {
                votingList.add(value)
            }
            field = value
        }

    val dayList = ArrayList<Day>()
    val votingList = ArrayList<Voting>()

    fun setPlayerRole(player: Player, role: Role) {
        if (role == Role.Doctor) {
            if (gameOptions.doctorInGame) {
                for (p in players) {
                    if (p.role == Role.Doctor)
                        p.role = Role.Red
                }
                player.role = role
            }
        }

        if (role == Role.Sheriff) {
            if (gameOptions.sheriffInGame) {
                for (p in players) {
                    if (p.role == Role.Sheriff)
                        p.role = Role.Red
                }
                player.role = role
            }
        }

        if (role == Role.Black) {
            var realBlackCount = 0

            for (p in players) {
                if (p.role == Role.Black) {
                    if (realBlackCount == gameOptions.blackCount - 1) {
                        p.role = Role.Red
                    } else {
                        realBlackCount++
                    }
                }
            }

            player.role = role
        }
        if (role == Role.Red)
            player.role = role
    }

    fun checkPlayersRoles(): Boolean {
        var sheriffIsReady = false
        var doctorIsReady = false
        var mafiaReadyCount = 0

        for (player in players) {
            if (player.role == Role.Doctor) {
                doctorIsReady = true
            }
            if (player.role == Role.Sheriff)
                sheriffIsReady = true
            if (player.role == Role.Black)
                mafiaReadyCount++
        }

        return (sheriffIsReady or !gameOptions.sheriffInGame) and (doctorIsReady or !gameOptions.doctorInGame) and (mafiaReadyCount == gameOptions.blackCount)
    }
    //---------------
    var deathPlayers: ArrayList<Player> = ArrayList()
}