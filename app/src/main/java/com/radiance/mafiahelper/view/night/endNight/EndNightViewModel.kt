package com.radiance.mafiahelper.view.night.endNight

import androidx.lifecycle.ViewModel
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game
import com.bsvt.core.roleCount

class EndNightViewModel : ViewModel() {
    private lateinit var game: Game

    fun init(game: Game) {
        this.game = game

        game.mafiaChoice.takeIf { it != game.doctorChoice  }?.let { dead ->
            game.characters.remove(dead)
            game.deathCharacters.add(dead)
        }
    }

    fun createGame(): Game {
        return game
    }

    fun gameIsEnded(): Boolean {
        return game.characters.roleCount(Role.Black) >= game.characters.roleCount(Role.Red)|| game.characters.roleCount(
            Role.Black) == 0
    }

    fun winnersTeam(): Role {
        if (game.characters.roleCount(Role.Black) == 0)
            return Role.Red
        else
            return Role.Black
    }

    fun clearChouce() {
        game.mafiaChoice = null
        game.doctorChoice = null
    }
}
