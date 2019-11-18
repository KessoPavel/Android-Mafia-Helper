package com.radiance.mafiahelper.view.day

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Day
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player

class DayViewModel(private val state: SavedStateHandle) : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    private var currentIndex = 0

    var players: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var playersInVoting: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var playersInNomination: MutableLiveData<ArrayList<Player>> = MutableLiveData()
    var currentPLayer: MutableLiveData<Player> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    private var day = Day()

    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController

        this.players.value = game.players
        this.playersInVoting.value = ArrayList()
        this.playersInNomination.value = ArrayList()
        this.currentPLayer.value = game.players.get(currentIndex)
    }

    fun playerInNomination(player: Player){
        if (playersInVoting.value!!.contains(player))
            return

        playersInNomination.value?.let {
            if (it.contains(player)){
                it.remove(player)
            } else {
                it.add(player)
            }

            playersInNomination.value = playersInNomination.value
        }
    }

    fun nextPlayer(){
        currentIndex++
        if (currentIndex == game.playersCont - 1) {
            gameIsReady.value = true
        }

        playersInVoting.value?.addAll(playersInNomination.value!!)
        playersInNomination.value?.clear()

        currentPLayer.value = players.value?.get(currentIndex)
    }

    fun createGame(): Game {
        playersInVoting.value?.addAll(playersInNomination.value!!)
        playersInNomination.value?.clear()

        val day = Day()
        day.addAll(playersInVoting.value!!)

        game.day = day

        return game
    }

    fun startVoting(direction: NavDirections){
        navController.navigate(direction)
    }
}
