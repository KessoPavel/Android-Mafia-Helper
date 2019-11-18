package com.radiance.mafiahelper.view.night

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player

class NightViewModel : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    var currentPlayer: MutableLiveData<Player> = MutableLiveData()
    var players = ArrayList<Player>()


    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController

        players = game.players
        currentPlayer.value = null
    }

    fun selectPlayer(player: Player) {
        currentPlayer.value = player
    }
}
