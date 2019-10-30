package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.dialogs.dialogSetPseudonym.SetPseudonymFragment
import com.radiance.mafiahelper.dialogs.dialogSetPseudonym.SetPseudonymListener
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_getting_to_known.*

class GettingToKnowFragment: GameFragment(),
    Adapter.ClickListener, SetPseudonymListener {
    override val layoutId: Int = R.layout.fragment_getting_to_known
    private lateinit var game: Game
    private lateinit var adapter: Adapter
    private lateinit var selectedPlayer: Player
    private val playerWithPseudonym = ArrayList<Player>()


    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        val recyclerView = view.findViewById<RecyclerView>(R.id.fgtk_player_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(createProviders(),this, this)
        recyclerView.adapter = adapter
        return view
    }

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

    override fun onClick(basePlayerProvider: BasePlayerProvider) {
        selectedPlayer = basePlayerProvider.player

        activity?.runOnUiThread{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.root_layout, SetPseudonymFragment.newInstance(this, selectedPlayer.pseudonym), "SetPseudonymFragment")
                ?.commit()
        }
    }

    override fun onLongClick(basePlayerProvider: BasePlayerProvider) {
    }


    override fun playerGotAnPseudonym(pseudonym: String) {
        if (selectedPlayer.pseudonym == ""){
            selectedPlayer.pseudonym = pseudonym
            playerWithPseudonym.add(selectedPlayer)

        } else {
            selectedPlayer.pseudonym = pseudonym
        }
        adapter.setData(createProviders())
        adapter.notifyDataSetChanged()

        if (playerWithPseudonym.size == game.playersCont){
            fgtk_first_gay.background = ContextCompat.getDrawable(context!!, R.drawable.ok_button)
            fgtk_first_gay.setOnClickListener{
                game.players = playerWithPseudonym
                listener.startFirstNight(game)
            }
        }
    }

    private fun createProviders(): ArrayList<BasePlayerProvider> {
        val answer = ArrayList<BasePlayerProvider>()

        for (i in 0 until  playerWithPseudonym.size){
            val manager = BasePlayerProvider(playerWithPseudonym[i], number =  (i + 1).toString())
            answer.add(manager)
        }

        for (player in game.players){
            if (!playerWithPseudonym.contains(player)){
                val manager = BasePlayerProvider(player, number =  "")
                answer.add(manager)
            }
        }

        return answer
    }
}