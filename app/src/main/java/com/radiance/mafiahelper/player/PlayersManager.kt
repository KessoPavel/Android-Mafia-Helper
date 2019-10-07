package com.radiance.mafiahelper.player

import kotlin.random.Random

object PlayersManager {
    fun loadPlayers(): Array<Player>{
        val players = generateRandomPlayers(25)
        setStatistic(players)
        return players
    }

    private fun setStatistic(players: Array<Player>){
        var bestRedIndex = 0
        var bestRedVictories = 0
        var bestBlackIndex = 0
        var bestBlackVictories = 0
        var bestDoctorIndex = 0
        var bestDoctorVictories = 0
        var bestSheriffIndex = 0
        var bestSheriffVictories = 0

        for (player in players){
            if (player.victoriesForReds > bestRedVictories) {
                bestRedIndex = players.indexOf(player)
                bestRedVictories = player.victoriesForReds
            }
            if (player.victoriesForBlacks > bestBlackVictories){
                bestBlackIndex = players.indexOf(player)
                bestBlackVictories = player.victoriesForBlacks
            }
            if (player.victoriesPerDoctor > bestDoctorVictories){
                bestDoctorIndex = players.indexOf(player)
                bestDoctorVictories = player.victoriesPerDoctor
            }
            if (player.victoriesPerSheriff > bestSheriffVictories){
                bestSheriffIndex = players.indexOf(player)
                bestSheriffVictories = player.victoriesPerSheriff
            }

            player.gameCount = player.gamesForBlacks +
                    player.gamesForReds +
                    player.gamesPerDoctor +
                    player.gamesPerSheriff
            player.victoriesCount = player.victoriesForBlacks +
                    player.victoriesForBlacks +
                    player.victoriesPerDoctor +
                    player.victoriesPerSheriff
        }

        if (players.isNotEmpty()) {
            players[bestRedIndex].isBestRed = true
            players[bestBlackIndex].isBestBlack = true
            players[bestDoctorIndex].isBestDoctor = true
            players[bestSheriffIndex].isBestSheriff = true
        }
    }

    fun getStatistic(player: Player): String{
        return "${player.victoriesCount} / ${player.gameCount}"
    }

    //region DEBUG
    private fun generateRandomPlayers(count : Int): Array<Player>{
        val players = ArrayList<Player>()

        for (i in 0 until count){
            val player = Player()
            player.name = "Player${i}"
            player.gamesPerSheriff = Random.nextInt(0, 50)
            player.gamesPerDoctor = Random.nextInt(0, 50)
            player.gamesForReds = Random.nextInt(0, 50)
            player.gamesForBlacks = Random.nextInt(0, 50)
            player.victoriesPerSheriff = Random.nextInt(0, 50)
            player.victoriesPerDoctor = Random.nextInt(0, 50)
            player.victoriesForBlacks = Random.nextInt(0, 50)
            player.victoriesForReds = Random.nextInt(0, 50)
            players.add(player)
        }

        return players.toTypedArray()
    }
    //endregion
}