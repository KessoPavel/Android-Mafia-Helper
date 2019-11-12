package com.radiance.mafiahelper.view.gameOptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.game.GameOptions
import com.radiance.mafiahelper.tools.MutableLiveObject

class SelectionGameOptionsViewModel(val state : SavedStateHandle) : ViewModel() {
    var gameOptions: MutableLiveObject<GameOptions> = MutableLiveObject()
    lateinit var game: Game

    fun init(game: Game){
        this.game = game

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

    private fun saveGameOptions() {
        state.set(GAME_OPTIONS, gameOptions.value)
    }

    companion object {
        const val GAME_OPTIONS = "game options"
    }

}
