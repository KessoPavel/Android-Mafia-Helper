package com.radiance.mafiahelper.view.firstNight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.view.firstNight.adapter.FirstNightAdapter

class FirstNightViewModel(private val state: SavedStateHandle) : ViewModel(), FirstNightAdapter.Holder.RoleListener {
    private lateinit var game: Game
    var players: MutableLiveData<ArrayList<Character>> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    fun init(game: Game) {
        this.game = game

        players.value = game.characters
        //todo
        //gameIsReady.value
    }

    fun createGame(): Game{
        return game
    }

    override fun setRole(role: com.bsvt.core.character.role.Role, character: Character) {
        character.role = role

        //todo
        //game.setRole()
        //gameIsReady.value = game.checkPlayersRoles()

        players.value = players.value
    }
}