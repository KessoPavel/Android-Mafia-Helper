package com.radiance.mafiahelper.view.day.endVoting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bsvt.core.character.role.Role
import com.bsvt.core.game.Game

import com.radiance.mafiahelper.R
import kotlinx.android.synthetic.main.end_voting_fragment.*

class EndVoting : Fragment() {
    private var game = Game()
    private val viewModel: EndVotingViewModel by viewModels()
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

        viewModel.init(game)


        val votingResult = viewModel.endVoting()

        if (votingResult.size == 1) {
            voting_result.text = votingResult[0].name + " goes to jail"

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
            findNavController().navigate(direction)
        }
    }

}
