package com.radiance.mafiahelper.game

import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role

class Game() {
    var players: ArrayList<Player> = ArrayList()
    var deathPlayers: ArrayList<Player> = ArrayList()
    private var isStarted = false

    val playersCont: Int
        get() = players.size

    val maxBlackCount: Int
        get() = playersCont / 4

    val minBlackCount: Int
        get() = 1

    var blackCount: Int = 0
    var doctorInGame: Boolean = false
    var sheriffInGame: Boolean = false

    var currentPlayerIndex = 0
    var votingList: ArrayList<Player> = ArrayList()
    var votingMap: HashMap<Player, Int> = HashMap<Player, Int>()

    val livePlayersCount: Int
        get() {
            var answer = 0

            for (player in players){
                if (!player.isDeath)
                    answer++
            }

            return answer
        }

    fun addPlayer(player: Player) {
        if (!isStarted) {
            if (!players.contains(player))
                players.add(player)
        }
    }

    fun removePlayer(player: Player) {
        if (!isStarted) {
            players.remove(player)
        }
    }

    fun cleatPseudonym() {
        for (player in players)
            player.pseudonym = ""
    }

    fun setPlayerRole(player: Player, role: Role) {
        if (role == Role.Doctor) {
            if (doctorInGame) {
                for (p in players) {
                    if (p.role == Role.Doctor)
                        p.role = Role.Red
                }
                player.role = role
            }
        }

        if (role == Role.Sheriff) {
            if (sheriffInGame) {
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
                    if (realBlackCount == blackCount - 1) {
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
        var gameIsReady = false

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

        return (sheriffIsReady or !sheriffInGame) and (doctorIsReady or !doctorInGame) and (mafiaReadyCount == blackCount)
    }

    fun endVoting(){
        currentPlayerIndex++
        if (currentPlayerIndex == playersCont)
            currentPlayerIndex = 0


        var deathPlayer: Player? = null
        var max = 0
        for (player in votingMap.keys){
            if (votingMap[player]!! > max){
                max = votingMap[player]!!
                deathPlayer = player
            }
        }

        deathPlayer?.isDeath = true
        deathPlayers.add(deathPlayer!!)
        players.remove(deathPlayer)

        votingMap.clear()
        votingList.clear()
    }

}