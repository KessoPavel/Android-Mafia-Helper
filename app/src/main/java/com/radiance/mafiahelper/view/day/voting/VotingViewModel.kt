package com.radiance.mafiahelper.view.day.voting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.bsvt.core.game.Voting

class VotingViewModel : ViewModel() {
    private lateinit var game: Game
    private lateinit var voting: Voting
    val currentPlayer: MutableLiveData<Character> = MutableLiveData()
    var gameIsReady: MutableLiveData<Boolean> = MutableLiveData()

    private var votingCount: Int = 0

    fun init(game: Game) {
        this.game = game

        voting = Voting(game.day!!.votingList)
        currentPlayer.value = voting.nextPlayer()
        gameIsReady.value = false

    }

    fun nextPlayer(votingValue: Int){
        votingCount += votingValue
        voting.votingResault(votingValue)
        currentPlayer.value = voting.nextPlayer()

        if (currentPlayer.value == null){
            gameIsReady.value = true
        }
    }

    fun minVotingCount(): Int {
        if (voting.isLastPlayer) {
            return maxVotingCount()
        }
        return 0
    }

    fun maxVotingCount(): Int {
        return game.characters.size - votingCount
    }

    fun createGame(): Game {
        game.voting = voting
        return game
    }
}
