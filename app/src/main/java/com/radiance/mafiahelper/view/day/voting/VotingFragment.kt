package com.radiance.mafiahelper.view.day.voting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bsvt.core.character.Character
import com.bsvt.core.game.Game
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.enter
import com.radiance.mafiahelper.out
import com.radiance.mafiahelper.setUpToolbar
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.voting_fragment.*

class VotingFragment : Fragment() {
    private var game = Game()
    private val viewModel: VotingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.voting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = VotingFragmentArgs.fromBundle(it)
            game = args.game
        }

        viewModel.currentPlayer.observe(this, Observer { player -> newPlayer(player) })
        viewModel.gameIsReady.observe(this, Observer { gameIsReady -> gameIsReady(gameIsReady) })

        viewModel.init(game)

        v_next_player.setOnClickListener{
            viewModel.nextPlayer(v_voting_count.value)
        }
        v_play.setOnClickListener{
            val direction = VotingFragmentDirections.actionVotingToEndVoting(viewModel.createGame())
            findNavController().navigate(direction)
        }

        setUpToolbar(mainToolbar, R.string.voting)
    }

    private fun gameIsReady(gameIsReady: Boolean){
        v_play.isClickable = gameIsReady
        v_next_player.isClickable = !gameIsReady

        if (gameIsReady) {
            v_play.enter()
            v_next_player.out()
        } else {
            v_play.out()
            v_next_player.enter()
        }
    }

    private fun newPlayer(player: Character?) {
        if (player == null){
            return
        }

        v_current_player_alias.text = player.name

        v_voting_count.maxValue = viewModel.maxVotingCount()
        v_voting_count.minValue = viewModel.minVotingCount()

        if (v_voting_count.maxValue == v_voting_count.minValue)
            v_voting_count.value = v_voting_count.minValue
    }
}
