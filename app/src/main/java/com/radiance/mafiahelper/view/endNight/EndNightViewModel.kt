package com.radiance.mafiahelper.view.endNight

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player

class EndNightViewModel : ViewModel() {
    private lateinit var game: Game
    private var player: Player? = null
    private lateinit var navController: NavController

    fun init(game: Game, player: Player?, navController: NavController) {
        this.game = game
        this.player = player
        this.navController = navController
    }

    fun createGame(): Game {
        player?.let {
            game.players.remove(it)
            game.deathPlayers.add(it)
        }

        return game
    }

    fun startDay(direction: NavDirections) {
        navController.navigate(direction)
    }
}
