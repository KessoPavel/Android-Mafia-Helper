package com.radiance.mafiahelper.player

import android.content.Context
import androidx.room.Room
import com.radiance.mafiahelper.room.PlayerDataBase
import com.radiance.mafiahelper.room.PlayerEntity
import kotlin.random.Random

object PlayersManager {
    const val dataBaseName = "player_database"
    var db: PlayerDataBase? = null

    fun init(context: Context){
        db = Room.databaseBuilder(context, PlayerDataBase::class.java, dataBaseName).allowMainThreadQueries().build()
    }

    fun loadPlayers(): ArrayList<Player> {
        val playerEntity = db!!.playerDao().getAll()
        return setStatistic(playerEntity)
    }

    fun addPlayer(player: Player) {
        db!!.playerDao().insert(
            PlayerEntity(
                name = player.name,
                gamesForBlacks = player.gamesForBlacks,
                gamesForReds = player.gamesForReds,
                gamesPerDoctor = player.gamesPerDoctor,
                gamesPerSheriff = player.gamesPerSheriff,
                victoriesForBlacks = player.victoriesForBlacks,
                victoriesForReds = player.victoriesForReds,
                victoriesPerDoctor = player.victoriesPerDoctor,
                victoriesPerSheriff = player.victoriesPerSheriff,
                firstNightDeaths = player.firstNightDeaths,
                firstVotingDeaths = player.firstVotingDeaths,
                votingDeaths = player.votingDeaths,
                nightDeaths = player.nightDeaths
        ))
    }

    private fun setStatistic(players: List<PlayerEntity>): ArrayList<Player> {
        val answer = ArrayList<Player>()

        var bestRedIndex = 0
        var bestRedVictories = 0
        var bestBlackIndex = 0
        var bestBlackVictories = 0
        var bestDoctorIndex = 0
        var bestDoctorVictories = 0
        var bestSheriffIndex = 0
        var bestSheriffVictories = 0

        for (player in players) {
            if (player.victoriesForReds > bestRedVictories) {
                bestRedIndex = players.indexOf(player)
                bestRedVictories = player.victoriesForReds
            }
            if (player.victoriesForBlacks > bestBlackVictories) {
                bestBlackIndex = players.indexOf(player)
                bestBlackVictories = player.victoriesForBlacks
            }
            if (player.victoriesPerDoctor > bestDoctorVictories) {
                bestDoctorIndex = players.indexOf(player)
                bestDoctorVictories = player.victoriesPerDoctor
            }
            if (player.victoriesPerSheriff > bestSheriffVictories) {
                bestSheriffIndex = players.indexOf(player)
                bestSheriffVictories = player.victoriesPerSheriff
            }

            answer.add(
                Player(
                    name = player.name,
                    gamesForBlacks = player.gamesForBlacks,
                    gamesForReds = player.gamesForReds,
                    gamesPerDoctor = player.gamesPerDoctor,
                    gamesPerSheriff = player.gamesPerSheriff,
                    victoriesForBlacks = player.victoriesForBlacks,
                    victoriesForReds = player.victoriesForReds,
                    victoriesPerDoctor = player.victoriesPerDoctor,
                    victoriesPerSheriff = player.victoriesPerSheriff,
                    firstNightDeaths = player.firstNightDeaths,
                    firstVotingDeaths = player.firstVotingDeaths,
                    votingDeaths = player.votingDeaths,
                    nightDeaths = player.nightDeaths,
                    gameCount = player.gamesForBlacks +
                            player.gamesForReds +
                            player.gamesPerDoctor +
                            player.gamesPerSheriff,
                    victoriesCount = player.victoriesForBlacks +
                            player.victoriesForBlacks +
                            player.victoriesPerDoctor +
                            player.victoriesPerSheriff
                )
            )
        }

        if (answer.isNotEmpty()) {
            answer[bestRedIndex].isBestRed = true
            answer[bestBlackIndex].isBestBlack = true
            answer[bestDoctorIndex].isBestDoctor = true
            answer[bestSheriffIndex].isBestSheriff = true
        }

        return answer
    }
}