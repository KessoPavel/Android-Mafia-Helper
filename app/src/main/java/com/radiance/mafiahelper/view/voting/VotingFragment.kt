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

import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.emptyGame
import com.radiance.mafiahelper.inflate
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
    private lateinit var adapter: RecyclerAdapter

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
        viewModel.players.observe(this, Observer { players -> updatePlayers(players) })
        viewModel.currentPlayer.observe(this, Observer { player -> newPlayer(player) })

        adapter = RecyclerAdapter(ArrayList(), VotingViewHolderBuilder(viewModel))
        v_recycler.layoutManager = LinearLayoutManager(context)
        v_recycler.adapter = adapter

        viewModel.init(game, findNavController())


        v_next_player.setOnClickListener{
            viewModel.nextPlayer(v_voting_count.value)
        }
    }

    private fun newPlayer(player: Player?) {
        if (player == null){
            v_next_player.visibility = View.INVISIBLE
            v_play.setOnClickListener{
                val direction = VotingFragmentDirections.actionVotingToEndVoting(viewModel.createGame())
                viewModel.endVoting(direction)
            }
            return
        }

        v_current_player_alias.text = player.pseudonym

        v_voting_count.maxValue = viewModel.maxVotingCount()
        v_voting_count.minValue = viewModel.minVotingCount()

        if (v_voting_count.maxValue == v_voting_count.minValue)
            v_voting_count.value = v_voting_count.minValue
    }

    private fun updatePlayers(players: HashMap<Player, Int>?) {
        val answer = ArrayList<PlayerHolder>()

        for (player in players!!.keys){
            answer.add(
                PlayerHolder(player= player, votingCount = players[player].toString())
            )
        }

        adapter.setData(answer)
        adapter.notifyDataSetChanged()
    }

    private class VotingViewHolderBuilder(private val viewModel: VotingViewModel): HolderBuilder{
        override fun build(parent: ViewGroup): Holder {
            val inflateView = parent.inflate(R.layout.voting_item, false)
            return VotingViewHolder(inflateView, viewModel)
        }

    }
}
