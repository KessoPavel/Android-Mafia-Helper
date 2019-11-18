package com.radiance.mafiahelper.view.firstNight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role

class FirstNightViewModel(private val state: SavedStateHandle) : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    var players: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController

        players.value = game.players
        gameIsReady.value = game.checkPlayersRoles()
    }

    fun createGame(): Game{
        return game
    }

    fun changeRole(player: Player, role: Role) {
        game.setPlayerRole(player, if (player.role == role) Role.Red else role)
        gameIsReady.value = game.checkPlayersRoles()
        players.value = players.value
    }

    fun startDay(direction: NavDirections) {
        navController.navigate(direction)
    }
}