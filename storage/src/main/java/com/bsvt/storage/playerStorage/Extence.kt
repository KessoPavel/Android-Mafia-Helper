package com.bsvt.storage.playerStorage

import com.bsvt.core.player.Player
import com.bsvt.core.player.statistics.DeathStatistics
import com.bsvt.core.player.statistics.GameStatistics
import com.bsvt.core.player.statistics.VictoriesStatistics
import com.bsvt.storage.playerStorage.room.PlayerEntity

fun PlayerEntity.toPlayer(): Player {
    val gameStatistics = GameStatistics.getEmpty()
    val deathStatistics = DeathStatistics.getEmpty()
    val victoriesStatistics = VictoriesStatistics.getEmpty()

    gameStatistics.gamesForBlacks = gamesForBlacks
    gameStatistics.gamesForReds = gamesForReds
    gameStatistics.gamesPerDoctor = gamesPerDoctor
    gameStatistics.gamesPerSheriff = gamesPerSheriff

    deathStatistics.firstNightDeaths = firstNightDeaths
    deathStatistics.firstVotingDeaths = firstVotingDeaths
    deathStatistics.nightDeaths = nightDeaths
    deathStatistics.votingDeaths = votingDeaths

    victoriesStatistics.victoriesForBlacks = victoriesForBlacks
    victoriesStatistics.victoriesForReds = victoriesForReds
    victoriesStatistics.victoriesPerDoctor = victoriesPerDoctor
    victoriesStatistics.victoriesPerSheriff = victoriesPerSheriff

    val name = this.name

    return Player.Builder().name(name).deathStatistics(deathStatistics)
        .gameStatistics(gameStatistics).victoriesStatistics(victoriesStatistics).build()
}

fun Player.toEntity(): PlayerEntity {
    return PlayerEntity(
        name= name,
        gamesPerSheriff = gameStatistics.gamesPerSheriff,
        gamesPerDoctor = gameStatistics.gamesPerDoctor,
        gamesForReds = gameStatistics.gamesForReds,
        gamesForBlacks = gameStatistics.gamesForBlacks,
        victoriesPerSheriff = victoriesStatistics.victoriesPerSheriff,
        victoriesPerDoctor = victoriesStatistics.victoriesPerDoctor,
        victoriesForReds = victoriesStatistics.victoriesForReds,
        victoriesForBlacks = victoriesStatistics.victoriesForBlacks,
        nightDeaths = deathStatistics.nightDeaths,
        votingDeaths = deathStatistics.votingDeaths,
        firstVotingDeaths = deathStatistics.firstVotingDeaths,
        firstNightDeaths = deathStatistics.firstNightDeaths
    )
}