package com.radiance.mafiahelper.view.endVoting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.player.Role
import kotlinx.android.synthetic.main.end_voting_fragment.*

class EndVoting : Fragment() {
    private var game = emptyGame()
    private lateinit var viewModel: EndVotingViewModel
    private lateinit var direction: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.end_voting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val args = EndVotingArgs.fromBundle(it)
            game = args.game
        }

        viewModel =
            ViewModelProvider(this, SavedStateVMFactory(this)).get(EndVotingViewModel::class.java)
        viewModel.init(game, findNavController())


        val votingResult = viewModel.endVoting()

        if (votingResult.size == 1) {
            voting_result.text = votingResult[0].pseudonym + " goes to jail"

            if (viewModel.gameIsEnded()) {
                when (viewModel.winnersTeam()) {
                    Role.Red -> {
                        direction = EndVotingDirections.redWinners(viewModel.createGame())
                    }
                    Role.Black -> {
                        direction = EndVotingDirections.blackWinners(viewModel.createGame())
                    }
                }
            } else {
                direction = EndVotingDirections.startNight(viewModel.createGame())
            }
        } else {
            voting_result.text = "new voting"
            direction = EndVotingDirections.startVoting(viewModel.createGame())
        }


        ev_play.setOnClickListener {
            viewModel.startVoting(direction)
        }
    }

}
