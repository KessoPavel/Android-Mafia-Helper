package com.radiance.mafiahelper.view.playersPicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayersManager

class PlayersPickerViewModel(val state : SavedStateHandle) : ViewModel() {
    var players: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var playersInGame: MutableLiveData<ArrayList<Player>> = MutableLiveData()

    private val PLAYER_IN_GAME = "PlayersInGame"
    private lateinit var navController: NavController

    init {
        playersInGame.value = state.get<ArrayList<Player>>(PLAYER_IN_GAME)?: ArrayList()
    }

    fun init(navController: NavController){
        this.navController = navController
        players.value = PlayersManager.loadPlayers()
    }

    fun game(): Game{
        return Game(playersInGame.value!!)
    }

    fun onPlayerClick(player: Player){
        if (playersInGame.value!!.contains(player)){
            playersInGame.value?.remove(player)
        } else {
            playersInGame.value?.add(player)
        }

        state.set(PLAYER_IN_GAME, playersInGame.value)
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

    fun addPlayer(player: Player?) {
        player?.let {
            players.value?.add(it)
            players.value = players.value
        }
    }
}