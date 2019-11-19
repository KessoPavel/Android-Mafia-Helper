package com.radiance.mafiahelper.view.endNight

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.shift

class EndNightViewModel : ViewModel() {
    private lateinit var game: Game
    private var player: Player? = null
    private lateinit var navController: NavController

    fun init(game: Game, player: Player?, navController: NavController) {
        this.game = game
        this.player = player
        this.navController = navController

        player?.let {
            game.players.remove(it)
            game.deathPlayers.add(it)
        }
    }

    fun createGame(): Game {
        return game
    }

    fun startDay(direction: NavDirections) {
        navController.navigate(direction)
    }

    fun gameIsEnded(): Boolean {
        return game.blackCount >= game.redCount || game.blackCount == 0
    }

    fun winnersTeam(): Role {
        if (game.blackCount == 0)
            return Role.Red
        else
            return Role.Black
    }
}
