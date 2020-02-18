package com.radiance.mafiahelper.view.day.day

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.game.Day
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.view.day.day.adapter.DayAdapter

class DayViewModel(private val state: SavedStateHandle) : ViewModel(), DayAdapter.Holder.Listener {
    private lateinit var game: Game
    private var currentIndex = 0

    var players: MutableLiveData<ArrayList<Character>> = MutableLiveData()
    var playersInVoting: MutableLiveData<ArrayList<Character>> = MutableLiveData()
    var playersInNomination: MutableLiveData<ArrayList<Character>> = MutableLiveData()
    var currentPLayer: MutableLiveData<Character> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()
    var playersEnd: MutableLiveData<Boolean> = MutableLiveData()

    fun init(game: Game) {
        this.game = game

        this.players.value = game.characters
        this.playersInVoting.value = ArrayList()
        this.playersInNomination.value = ArrayList()
        this.currentPLayer.value = game.characters[currentIndex]
        this.gameIsReady.value = false
        this.playersEnd.value = false
    }

    override fun characterClick(character: Character) {
        if (playersInVoting.value!!.contains(character))
            return

        playersInNomination.value?.let {
            if (it.contains(character)){
                it.remove(character)
            } else {
                it.add(character)
            }

            playersInNomination.value = playersInNomination.value
        }

        if (playersInVoting.value?.size == 0) {
            if (currentIndex == game.characters.size - 1) {
                gameIsReady.value = playersInNomination.value?.size != 0
            }
        }
    }

    fun nextPlayer(){
        currentIndex++
        if (currentIndex == game.characters.size - 1) {
            gameIsReady.value = true && playersInVoting.value?.size != 0
            playersEnd.value = true
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
}
