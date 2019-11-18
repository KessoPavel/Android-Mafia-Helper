package com.radiance.mafiahelper.view.endVoting

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder

class EndVotingViewModel : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    private var votingResault = ArrayList<Player>()

    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController
    }

    fun createGame() : Game{
        if (votingResault.size == 1){
            game.players.remove(votingResault[0])
            game.deathPlayers.add(votingResault[0])
        } else {
            game.day!!.votingList = votingResault
        }

        return game
    }

    fun endVoting(): ArrayList<Player> {
        votingResault = game.voting!!.endVoting()
        return votingResault
    }

    fun startNight(direction: NavDirections){
        navController.navigate(direction)
    }

    fun startVoting(direction: NavDirections){
        navController.navigate(direction)
    }
}
