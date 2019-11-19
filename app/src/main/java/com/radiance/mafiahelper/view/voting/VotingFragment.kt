package com.radiance.mafiahelper.view.voting

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.*

import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import com.radiance.mafiahelper.view.adapter.HolderBuilder
import com.radiance.mafiahelper.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.voting_fragment.*
import java.util.HashMap

class VotingFragment : Fragment() {
    private var game = emptyGame()
    private lateinit var viewModel: VotingViewModel

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

        viewModel = ViewModelProvider(this, SavedStateVMFactory(this)).get(VotingViewModel::class.java)
        viewModel.currentPlayer.observe(this, Observer { player -> newPlayer(player) })
        viewModel.gameIsReady.observe(this, Observer { gameIsReady -> gameIsReady(gameIsReady) })

        viewModel.init(game, findNavController())


        v_next_player.setOnClickListener{
            viewModel.nextPlayer(v_voting_count.value)
        }
        v_play.setOnClickListener{
            val direction = VotingFragmentDirections.actionVotingToEndVoting(viewModel.createGame())
            viewModel.endVoting(direction)
        }
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

    private fun newPlayer(player: Player?) {
        if (player == null){
            return
        }

        v_current_player_alias.text = player.pseudonym

        v_voting_count.maxValue = viewModel.maxVotingCount()
        v_voting_count.minValue = viewModel.minVotingCount()

        if (v_voting_count.maxValue == v_voting_count.minValue)
            v_voting_count.value = v_voting_count.minValue
    }
}
