package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_voting.*
import kotlinx.android.synthetic.main.fragment_voting.view.*

class VotingFragment: BaseFragment(){
    override val layoutId: Int = R.layout.fragment_voting
    override val title: Int = R.string.voting

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        game = arguments?.getSerializable("GAME") as Game

        view.fv_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(createProviders(), this, this)
        view.fv_recycler_view.adapter = adapter
        return view
    }

    private lateinit var game: Game
    private var votingList: ArrayList<Player> = ArrayList()
    private var currentPlayerIndex: Int = 0
    private var currentVotingNumber: Int = 0
    private lateinit var adapter: Adapter

    companion object{
        const val TAG = "VotingFragment"

        fun newInstance(game: Game): VotingFragment {
            val fragment = VotingFragment()
            fragment.game = game
            return fragment
        }
    }

    private fun createProviders(): java.util.ArrayList<PlayerHolder> {
        val answer = ArrayList<PlayerHolder>()

        for (player in game.votingMap.keys){
            val manager = PlayerHolder(player, votingCount = game.votingMap[player].toString())
            answer.add(manager)
        }

        return answer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        votingList = game.votingList
        setCurrentPlayer(votingList[currentPlayerIndex], game.livePlayersCount, 0)

        fv_next_player.setOnClickListener{
            nextPlayer()
        }
        fv_number_picker.setOnValueChangedListener{
            _, _, newVal ->
                currentVotingNumber = newVal
        }

        fv_toolbar.text = getString(title)
    }

    private fun nextPlayer(){
        game.votingMap[votingList[currentPlayerIndex]] = currentVotingNumber
        currentVotingNumber = 0

        adapter.setData(createProviders())
        adapter.notifyDataSetChanged()

        currentPlayerIndex++
        if (currentPlayerIndex == votingList.size) {
            fv_button.setOnClickListener{
                game.endVoting()

                val args = Bundle()
                args.putSerializable("GAME", game)
                findNavController().navigate(R.id.action_votingFragment_to_dayFragment, args)
            }
            fv_next_player.visibility = View.INVISIBLE
            fv_next_player.setOnClickListener(null)
            return
        }

        var max = game.livePlayersCount
        for (player in game.votingMap.keys){
            max -= game.votingMap[player]!!
        }

        var min = 0
        if (currentPlayerIndex == votingList.size - 1 || votingList.size == 1) {
            min = max
            fv_number_picker.value = min
            currentVotingNumber = min
        }

        setCurrentPlayer(votingList[currentPlayerIndex], max, min)
    }

    private fun setCurrentPlayer(player: Player, max: Int, min: Int){
        fv_current_name.text = player.name
        fv_current_pseudonym.text = player.pseudonym
        fv_number_picker.maxValue = max
        fv_number_picker.minValue = if (votingList.size == 1) max else min
        currentVotingNumber = if (votingList.size == 1) max else min
    }
}