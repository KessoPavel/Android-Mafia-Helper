package com.radiance.mafiahelper.view.night.firstNight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.view.night.firstNight.adapter.FirstNightAdapter

class FirstNightViewModel(private val state: SavedStateHandle) : ViewModel(), FirstNightAdapter.Holder.RoleListener {
    private lateinit var game: Game
    var players: MutableLiveData<ArrayList<Character>> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    fun init(game: Game) {
        this.game = game

        players.value = game.characters
        gameIsReady.value = game.checkRole()
    }

    fun createGame(): Game{
        return game
    }

    override fun setRole(role: com.bsvt.core.character.role.Role, character: Character) {
        if (character.role == role) {
            character.role = Role.Red
        } else {
            game.setRole(role, character)
        }
        gameIsReady.value = game.checkRole()

        players.value = players.value
    }
}