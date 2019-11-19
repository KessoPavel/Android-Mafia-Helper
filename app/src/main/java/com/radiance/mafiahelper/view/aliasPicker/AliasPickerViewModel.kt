package com.radiance.mafiahelper.view.aliasPicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import java.util.*
import kotlin.collections.ArrayList

class AliasPickerViewModel(private val state: SavedStateHandle) : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    var players: MutableLiveData<ArrayList<Player>> = MutableLiveData()

    fun init(game: Game, navController: NavController){
        this.game = game
        this.navController = navController

        players.value = game.players
    }

    fun addAlias(alias: String, player: Player) {
        player.pseudonym = alias
    }

    fun changePlayerPosition(from: Int, to: Int) {
        Collections.swap(players.value, from, to)
    }

    fun goToFirstNight(direction: NavDirections){
        navController.navigate(direction)
    }

    fun createGame(): Game {
        for (player in game.players){
            if (player.pseudonym == "")
                player.pseudonym = player.name
        }

        return game
    }
}
