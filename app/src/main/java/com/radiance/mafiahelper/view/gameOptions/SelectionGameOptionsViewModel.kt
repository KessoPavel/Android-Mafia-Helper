package com.radiance.mafiahelper.view.gameOptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.game.GameOptions
import com.radiance.mafiahelper.tools.MutableLiveObject

class SelectionGameOptionsViewModel(private val state : SavedStateHandle) : ViewModel() {
    var gameOptions: MutableLiveObject<GameOptions> = MutableLiveObject()
    lateinit var game: Game
    lateinit var navController: NavController

    fun init(game: Game, navController: NavController){
        this.game = game
        this.navController = navController

        gameOptions.value = state.get(GAME_OPTIONS)?: GameOptions()
        gameOptions.value?.playersCount = game.players.size
    }

    fun doctorCheck(){
        gameOptions.value?.doctorInGame = !gameOptions.value?.doctorInGame!!
        saveGameOptions()
    }

    fun sheriffCheck(){
        gameOptions.value?.sheriffInGame = !gameOptions.value?.sheriffInGame!!
        saveGameOptions()
    }

    fun blackCount(count: Int) {
        gameOptions.value?.blackCount = count
        saveGameOptions()
    }

    fun clickPlay(direction: NavDirections){
        navController.navigate(direction)
    }

    fun createGame(): Game {
        game.gameOptions = gameOptions.value?: GameOptions()
        return game
    }

    private fun saveGameOptions() {
        state.set(GAME_OPTIONS, gameOptions.value)
    }

    companion object {
        const val GAME_OPTIONS = "game options"
    }

}
