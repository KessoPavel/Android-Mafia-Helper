package com.radiance.mafiahelper.gameFragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.gettingToKnowFrgment.GettingToKnowAdapter
import com.radiance.mafiahelper.gettingToKnowFrgment.GettingToKnownItemListener
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.playerProvider.GettingToKnownDisplayManager
import com.radiance.mafiahelper.dialogSetPseudonym.SetPseudonymFragment
import com.radiance.mafiahelper.dialogSetPseudonym.SetPseudonymListener
import kotlinx.android.synthetic.main.fragment_getting_to_known.*

class GettingToKnowFragment: GameFragment(),
    GettingToKnownItemListener, SetPseudonymListener {
    override val layoutId: Int = R.layout.fragment_getting_to_known

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        val recyclerView = view.findViewById<RecyclerView>(R.id.fgtk_player_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = GettingToKnowAdapter(
            displayManagersFromPlayers(),
            this
        )
        recyclerView.adapter = adapter
        return view
    }

    private lateinit var game: Game
    private lateinit var adapter: GettingToKnowAdapter
    private lateinit var selectedPlayer: Player
    private val playerWithPseudonym = ArrayList<Player>()

    companion object {
        const val TAG = "GettingToKnowFragment"

        fun newInstance(game: Game): GettingToKnowFragment {
            val fragment = GettingToKnowFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game.cleatPseudonym()
        fgtk_first_gay.background = ContextCompat.getDrawable(context!!, R.drawable.not_enabled_button)
    }

    override fun clickToPlayer(player: Player) {
        selectedPlayer = player

        activity?.runOnUiThread{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.root_layout, SetPseudonymFragment.newInstance(this, selectedPlayer.pseudonym), "SetPseudonymFragment")
                ?.commit()
        }
    }


    override fun playerGotAnPseudonym(pseudonym: String) {
        if (selectedPlayer.pseudonym == ""){
            selectedPlayer.pseudonym = pseudonym
            playerWithPseudonym.add(selectedPlayer)

        } else {
            selectedPlayer.pseudonym = pseudonym
        }
        adapter.replaceData(displayManagersFromPlayers())
        adapter.notifyDataSetChanged()

        if (playerWithPseudonym.size == game.playersCont){
            fgtk_first_gay.background = ContextCompat.getDrawable(context!!, R.drawable.ok_button)
            fgtk_first_gay.setOnClickListener{
                game.players = playerWithPseudonym
                listener.startFirstNight(game)
            }
        }
    }

    private fun displayManagersFromPlayers(): List<GettingToKnownDisplayManager> {
        val answer = ArrayList<GettingToKnownDisplayManager>()

        for (i in 0 until  playerWithPseudonym.size){
            val manager = GettingToKnownDisplayManager(playerWithPseudonym[i], (i + 1).toString())
            answer.add(manager)
        }

        for (player in game.players){
            if (!playerWithPseudonym.contains(player)){
                val manager = GettingToKnownDisplayManager(player, "")
                answer.add(manager)
            }
        }

        return answer
    }
}