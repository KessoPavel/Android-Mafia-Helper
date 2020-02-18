package com.radiance.mafiahelper.view.day.endVoting

import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game
import com.bsvt.core.roleCount
import com.radiance.mafiahelper.shift

class EndVotingViewModel : ViewModel() {
    private lateinit var game: Game
    private var votingResault = ArrayList<Character>()

    fun init(game: Game) {
        this.game = game
    }

    fun createGame() : Game{
        return game
    }

    fun endVoting(): ArrayList<Character> {
        votingResault = game.voting!!.endVoting()

        if (votingResault.size == 1){
            game.characters = shift(game.characters, 1)

            game.characters.remove(votingResault[0])
            game.deathCharacters.add(votingResault[0])

        } else {
            game.day!!.votingList = votingResault
        }

        return votingResault
    }

    fun gameIsEnded(): Boolean {
        return game.characters.roleCount(Role.Black) >= game.characters.roleCount(Role.Red)|| game.characters.roleCount(Role.Black) == 0
    }

    fun winnersTeam(): Role {
        if (game.characters.roleCount(Role.Black) == 0)
            return Role.Red
        else
            return Role.Black
    }
}
