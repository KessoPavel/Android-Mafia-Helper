package com.radiance.mafiahelper.view.voting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.game.Voting
import com.radiance.mafiahelper.player.Player

class VotingViewModel : ViewModel() {
    private lateinit var game: Game
    private lateinit var navController: NavController
    private lateinit var voting: Voting
    var players: MutableLiveData<HashMap<Player, Int>> = MutableLiveData()
    val currentPlayer: MutableLiveData<Player> = MutableLiveData()

    private var votingCount: Int = 0


    fun init(game: Game, navController: NavController) {
        this.game = game
        this.navController = navController

        voting = Voting(game.day!!.votingList)
        currentPlayer.value = voting.nextPlayer()
        players.value = voting.votingMap
    }

    fun nextPlayer(votingValue: Int){
        votingCount += votingValue
        voting.votingResault(votingValue)
        currentPlayer.value = voting.nextPlayer()
        players.value = voting.votingMap
    }

    fun minVotingCount(): Int {
        if (voting.isLastPlayer) {
            return maxVotingCount()
        }
        return 0
    }

    fun maxVotingCount(): Int {
        return game.playersCont - votingCount
    }

    fun createGame(): Game {
        game.voting = voting
        return game
    }

    fun endVoting(direction: NavDirections) {
        navController.navigate(direction)
    }
}
