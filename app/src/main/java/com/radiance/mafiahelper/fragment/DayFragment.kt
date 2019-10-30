package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_day.*
import kotlinx.android.synthetic.main.fragment_day.view.*

class DayFragment: GameFragment(), Adapter.ClickListener {
    override val layoutId: Int = R.layout.fragment_day

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        view.fd_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(createProviders(), this, this)
        view.fd_recycler_view.adapter = adapter
        return view
    }

    private lateinit var game: Game
    private var currentPlayerIndex = 0
    private lateinit var adapter: Adapter

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
        adapter.setData(createProviders())
        adapter.notifyDataSetChanged()
    }

    private fun setCurrentPlayer(player: Player){
        fd_current_name.text = player.name
        fd_current_pseudonym.text = player.pseudonym
    }

    private fun createProviders(): ArrayList<BasePlayerProvider>{
        val answer = ArrayList<BasePlayerProvider>()

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

                answer.add(BasePlayerProvider(player, status = status, isClickable = isClickable, number = number))
            }
        }

        return answer
    }

    override fun onClick(basePlayerProvider: BasePlayerProvider) {
        if (votingList.contains(basePlayerProvider.player)){
            votingList.remove(basePlayerProvider.player)
            newInVoting.remove(basePlayerProvider.player)
        } else {
            votingList.add(basePlayerProvider.player)
            newInVoting.add(basePlayerProvider.player)
        }

        adapter.setData(createProviders())
        adapter.notifyDataSetChanged()
    }

    override fun onLongClick(basePlayerProvider: BasePlayerProvider) {
    }
}