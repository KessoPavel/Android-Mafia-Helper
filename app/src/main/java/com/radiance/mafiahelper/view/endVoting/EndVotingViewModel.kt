package com.radiance.mafiahelper.view.endVoting

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.shift

class EndVotingViewModel : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    private var votingResault = ArrayList<Player>()

    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController
    }

    fun createGame() : Game{
        return game
    }

    fun endVoting(): ArrayList<Player> {
        votingResault = game.voting!!.endVoting()

        if (votingResault.size == 1){
            game.players = shift(game.players, 1)

            game.players.remove(votingResault[0])
            game.deathPlayers.add(votingResault[0])

        } else {
            game.day!!.votingList = votingResault
        }



        return votingResault
    }

    fun startNight(direction: NavDirections){
        navController.navigate(direction)
    }

    fun startVoting(direction: NavDirections){
        navController.navigate(direction)
    }

    fun gameIsEnded(): Boolean {
        return game.blackCount >= game.redCount || game.blackCount == 0
    }

    fun winnersTeam(): Role {
        if (game.blackCount == 0)
            return Role.Red
        else
            return Role.Black
    }
}
