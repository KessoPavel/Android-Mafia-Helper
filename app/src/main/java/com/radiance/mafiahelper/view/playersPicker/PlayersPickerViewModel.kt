package com.radiance.mafiahelper.view.playersPicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayersManager

class PlayersPickerViewModel : ViewModel() {
    var players: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var playersInGame: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    lateinit var navController: NavController

    fun init(navController: NavController){
        this.navController = navController
        players.value = PlayersManager.loadPlayers()
        playersInGame.value = ArrayList()
    }

    fun game(): Game{
        val game = Game()
        game.players = playersInGame.value!!

        return game
    }

    fun onPlayerClick(player: Player){
        if (playersInGame.value!!.contains(player)){
            playersInGame.value?.remove(player)
        } else {
            playersInGame.value?.add(player)
        }

        playersInGame.value = playersInGame.value
    }

    fun onAddPlayerClick(direction: NavDirections){
        navController.navigate(direction)
    }

    fun onPlayerLongClick(direction: NavDirections){
        navController.navigate(direction)
    }

    fun onPlayClick(direction: NavDirections){
        navController.navigate(direction)
    }
}