package com.radiance.mafiahelper.view.night.doctorFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.view.night.doctorFragment.adapter.DoctorAdapter

class DoctorFragmentViewModel : ViewModel(), DoctorAdapter.Holder.Listener {
    private lateinit var game: Game
    var currentPlayer: MutableLiveData<Character> = MutableLiveData()
    var characters: MutableLiveData<ArrayList<Character>> = MutableLiveData()

    fun init(game: Game) {
        this.game = game

        characters.value = game.characters
        currentPlayer.value = null
    }

    override fun characterClick(character: Character) {
        currentPlayer.value = character
    }

    fun createGame(): Game {
        game.doctorChoice = currentPlayer.value
        return game
    }
}
