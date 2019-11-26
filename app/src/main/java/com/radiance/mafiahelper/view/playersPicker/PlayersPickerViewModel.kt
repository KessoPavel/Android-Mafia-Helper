package com.radiance.mafiahelper.view.playersPicker

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayersManager

class PlayersPickerViewModel(val state : SavedStateHandle) : ViewModel() {
    var filter: String = ""
        set(value) {
            players.value = ArrayList(loadedPlayers.filter {
                it.name.contains(value, ignoreCase = true)
            })
        }
    var loadedPlayers = ArrayList<Player>()
    var players: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var playersInGame: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    private val PLAYER_IN_GAME = "PlayersInGame"
    private lateinit var navController: NavController

    init {
        playersInGame.value = state.get<ArrayList<Player>>(PLAYER_IN_GAME)?: ArrayList()
    }

    fun init(navController: NavController, context: Context){
        this.navController = navController
        PlayersManager.init(context)
        loadedPlayers = PlayersManager.loadPlayers()
        players.value = loadedPlayers
        gameIsReady.value = playersInGame.value?.size!! >= minPlayersCount
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

        if (gameIsReady.value != (playersInGame.value?.size!! >= minPlayersCount)) {
            gameIsReady.value = playersInGame.value?.size!! >= minPlayersCount
        }
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
            PlayersManager.addPlayer(it)
            players.value = PlayersManager.loadPlayers()
        }
    }

    companion object {
        const val minPlayersCount: Int = 5
    }
}