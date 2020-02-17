package com.radiance.mafiahelper.view.aliasPicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.view.aliasPicker.adapter.AliasAdapter
import java.util.*

class AliasPickerViewModel(private val state: SavedStateHandle) : ViewModel(), AliasAdapter.Holder.AliasListener {
    private lateinit var game: Game
    var players: MutableLiveData<ArrayList<Character>> = MutableLiveData()

    fun init(game: Game){
        this.game = game

        players.value = game.characters
    }

    override fun addAlias(alias: String, player: Character) {
        player.name = alias
    }

    fun changePlayerPosition(from: Int, to: Int) {
        players.value?.let {
            Collections.swap(it, from, to)
        }
    }


    fun createGame(): Game {
        for (player in game.characters){
            if (player.name == "")
                player.name = player.player.name
        }

        return game
    }
}
