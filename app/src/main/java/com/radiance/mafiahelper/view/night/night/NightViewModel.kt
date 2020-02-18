package com.radiance.mafiahelper.view.night.night

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.view.night.night.adapter.NightAdapter

class NightViewModel : ViewModel(), NightAdapter.Holder.Listener {
    private lateinit var game: Game
    var currentPlayer: MutableLiveData<Character> = MutableLiveData()
    var players = ArrayList<Character>()


    fun init(game: Game) {
        this.game = game

        players = game.characters
        currentPlayer.value = null
    }

    override fun clickPlayer(character: Character) {
        currentPlayer.value = character

    }

    fun createGame(): Game {
        return game
    }
}
