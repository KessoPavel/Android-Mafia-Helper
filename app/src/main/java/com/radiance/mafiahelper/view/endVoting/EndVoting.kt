package com.radiance.mafiahelper.view.endVoting

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import kotlinx.android.synthetic.main.end_voting_fragment.*

class EndVoting : Fragment() {
    private var game = emptyGame()
    private lateinit var viewModel: EndVotingViewModel

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

        viewModel = ViewModelProvider(this, SavedStateVMFactory(this)).get(EndVotingViewModel::class.java)
        viewModel.init(game, findNavController())


        val voting = viewModel.endVoting()

        if (voting.size == 1){
            voting_result.text = voting[0].pseudonym + " goes to jail"
            ev_play.setOnClickListener{
                val destination = EndVotingDirections.startNight(viewModel.createGame())
                viewModel.startVoting(destination)
            }
        } else {
            voting_result.text = "new voting"
            ev_play.setOnClickListener{
                val destination = EndVotingDirections.startVoting(viewModel.createGame())
                viewModel.startVoting(destination)
            }
        }
    }

}
