package com.radiance.mafiahelper.gameFragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.dayFragment.DayFragmentAdapter
import com.radiance.mafiahelper.dayFragment.DayPlayerClickListener
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.playerProvider.DayDisplayManager
import kotlinx.android.synthetic.main.fragment_day.*
import kotlinx.android.synthetic.main.fragment_day.view.*

class DayFragment: GameFragment(), DayPlayerClickListener {
    override val layoutId: Int = R.layout.fragment_day

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        view.fd_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter =
            DayFragmentAdapter(createDisplayManagers(), this)
        view.fd_recycler_view.adapter = adapter
        return view
    }

    private lateinit var game: Game
    private var currentPlayerIndex = 0
    private lateinit var adapter: DayFragmentAdapter

    private val votingList = ArrayList<Player>()
    private val newInVoting = ArrayList<Player>()

    companion object{
        fun newInstance(game: Game): DayFragment {
            val fragment = DayFragment()
            fragment.game = game
            return fragment
        }
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