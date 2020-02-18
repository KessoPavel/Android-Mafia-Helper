package com.radiance.mafiahelper.view.night.endNight

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game
import com.bsvt.core.roleCount

class EndNightViewModel : ViewModel() {
    private lateinit var game: Game
    private var player: Character? = null

    fun init(game: Game, player: Character?) {
        this.game = game
        this.player = player

        player?.let {
            game.characters.remove(it)
            game.deathCharacters.add(it)
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
}
