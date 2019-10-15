package com.radiance.mafiahelper.dayFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.playerDisplayManager.DayDisplayManager
import kotlinx.android.synthetic.main.fragment_day.*
import kotlinx.android.synthetic.main.fragment_day.view.*

class DayFragment: Fragment(), DayPlayerClickListener {
    private lateinit var game: Game
    private lateinit var listener: StartVotingListener
    private var currentPlayerIndex = 0
    private lateinit var adapter: DayFragmentAdapter

    private val votingList = ArrayList<Player>()
    private val newInVoting = ArrayList<Player>()

    companion object{
        const val TAG = "DayFragment"

        fun newInstance(game: Game): DayFragment {
            val fragment = DayFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is StartVotingListener){
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement StartVotingListener.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)
        view.fd_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = DayFragmentAdapter(createDisplayManagers(), this)
        view.fd_recycler_view.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCurrentPlayer(game.players[game.currentPlayerIndex + currentPlayerIndex])
        fd_next_player.setOnClickListener{
            nextPlayer()
        }
        fd_button.background = ContextCompat.getDrawable(context!!, R.drawable.not_enabled_button)
    }

    private fun nextPlayer(){
        currentPlayerIndex++

        if (currentPlayerIndex >= game.playersCont - 1) {
            fd_next_player.setOnClickListener(null)
            fd_next_player.visibility = View.INVISIBLE
            fd_button.background = ContextCompat.getDrawable(context!!, R.drawable.ok_button)
            fd_button.setOnClickListener{
                game.votingList = votingList

                listener.startVoting(game)
            }
        }

        var nextIndex = 0

        nextIndex = if (currentPlayerIndex + game.currentPlayerIndex >= game.playersCont){
            currentPlayerIndex + game.currentPlayerIndex - game.playersCont
        } else {
            currentPlayerIndex + game.currentPlayerIndex
        }

        setCurrentPlayer(player = game.players[nextIndex])

        newInVoting.clear()
        adapter.setData(createDisplayManagers())
        adapter.notifyDataSetChanged()
    }

    private fun setCurrentPlayer(player: Player){
        fd_current_name.text = player.name
        fd_current_pseudonym.text = player.pseudonym
    }

    private fun createDisplayManagers(): ArrayList<DayDisplayManager>{
        val answer = ArrayList<DayDisplayManager>()

        for (player in game.players){
            if (!player.isDeath){
                var isClickable = true
                var number = ""
                var status = ""

                if (votingList.contains(player)){
                    isClickable = newInVoting.contains(player)
                    number = (votingList.indexOf(player) + 1).toString()
                    status = if (newInVoting.contains(player)) "" else "in voting"
                }

                answer.add(DayDisplayManager(player, status, isClickable, number))
            }
        }

        return answer
    }

    override fun click(player: Player) {
        if (votingList.contains(player)){
            votingList.remove(player)
            newInVoting.remove(player)
        } else {
            votingList.add(player)
            newInVoting.add(player)
        }

        adapter.setData(createDisplayManagers())
        adapter.notifyDataSetChanged()
    }
}