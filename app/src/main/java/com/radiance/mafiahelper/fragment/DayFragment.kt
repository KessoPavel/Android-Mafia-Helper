package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_day.*
import kotlinx.android.synthetic.main.fragment_day.view.*

class DayFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_day
    override val title: Int = R.string.day

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        game = arguments?.getSerializable("GAME") as Game
        view.fd_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(createProviders(game.players), this, this)
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

        fd_toolbar.text = getString(title)
    }

    private fun nextPlayer(){
        currentPlayerIndex++

        if (currentPlayerIndex >= game.playersCont - 1) {
            fd_next_player.setOnClickListener(null)
            fd_next_player.visibility = View.INVISIBLE
            fd_button.background = ContextCompat.getDrawable(context!!, R.drawable.ok_button)
            fd_button.setOnClickListener{
                game.votingList = votingList
                val args = Bundle()
                args.putSerializable("GAME", game)
                findNavController().navigate(R.id.action_dayFragment_to_votingFragment, args)
            }
        }


        var nextIndex = if (currentPlayerIndex + game.currentPlayerIndex >= game.playersCont){
            currentPlayerIndex + game.currentPlayerIndex - game.playersCont
        } else {
            currentPlayerIndex + game.currentPlayerIndex
        }

        setCurrentPlayer(player = game.players[nextIndex])

        newInVoting.clear()
        adapter.setData(createProviders(game.players))
        adapter.notifyDataSetChanged()
    }

    private fun setCurrentPlayer(player: Player){
        fd_current_name.text = player.name
        fd_current_pseudonym.text = player.pseudonym
    }

    override fun onClick(playerHolder: PlayerHolder) {
        if (votingList.contains(playerHolder.player)){
            votingList.remove(playerHolder.player)
            newInVoting.remove(playerHolder.player)
        } else {
            votingList.add(playerHolder.player)
            newInVoting.add(playerHolder.player)
        }

        adapter.setData(createProviders(game.players))
        adapter.notifyDataSetChanged()
    }

    override fun createProvider(player: Player): PlayerHolder? {
        if (!player.isDeath){
            var isClickable = true
            var number = ""
            var status = ""

            if (votingList.contains(player)){
                isClickable = newInVoting.contains(player)
                number = (votingList.indexOf(player) + 1).toString()
                status = if (newInVoting.contains(player)) "" else "enter voting"
            }

            return PlayerHolder(
                    player,
                    status = status,
                    isClickable = isClickable,
                    number = number)
        } else {
            return null
        }
    }
}