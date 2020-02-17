package com.bsvt.core.player

import com.bsvt.core.player.statistics.DeathStatistics
import com.bsvt.core.player.statistics.GameStatistics
import com.bsvt.core.player.statistics.VictoriesStatistics
import java.io.Serializable

class Player private constructor(
    var name: String,
    val gameStatistics: GameStatistics,
    val victoriesStatistics: VictoriesStatistics,
    val deathStatistics: DeathStatistics
    ): Serializable {

    class Builder {
        private var name = ""
        private var gameStatistics = GameStatistics.getEmpty()
        private var victoriesStatistics = VictoriesStatistics.getEmpty()
        private var deathStatistics = DeathStatistics.getEmpty()

        fun name(name: String) = apply { this.name = name }
        fun gameStatistics(gameStatistics: GameStatistics) = apply { this.gameStatistics = gameStatistics }
        fun victoriesStatistics(victoriesStatistics: VictoriesStatistics) = apply { this.victoriesStatistics = victoriesStatistics }
        fun deathStatistics(deathStatistics: DeathStatistics) = apply { this.deathStatistics = deathStatistics }

        fun build() = Player(
            name,
            gameStatistics,
            victoriesStatistics,
            deathStatistics
        )
    }
}