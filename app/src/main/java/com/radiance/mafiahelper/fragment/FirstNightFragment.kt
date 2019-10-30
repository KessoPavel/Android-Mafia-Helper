package com.radiance.mafiahelper.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Role
import kotlinx.android.synthetic.main.fragment_first_night.view.*
import android.os.Handler
import androidx.core.content.ContextCompat
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.adapter.Adapter
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_first_night.*


class FirstNightFragment: GameFragment(),
    Adapter.HolderListener {
    override fun onClick(basePlayerProvider: BasePlayerProvider) {
    }

    override fun onLongClick(basePlayerProvider: BasePlayerProvider) {
    }

    override fun playerRoleChanged(basePlayerProvider: BasePlayerProvider, role: Role) {
        game.setPlayerRole(basePlayerProvider.player, role)

        if (game.checkPlayersRoles()){
            ffn_button.background = ContextCompat.getDrawable(context!!, R.drawable.ok_button)
            ffn_button.setOnClickListener{
                listener.startDay(game)
            }
        } else {
            ffn_button.background = ContextCompat.getDrawable(context!!, R.drawable.not_enabled_button)
            ffn_button.setOnClickListener(null)
        }

        adapter.setData(createDisplayManagers())


        Handler().post {
            adapter.notifyDataSetChanged()
        }
    }

    override val layoutId: Int = R.layout.fragment_first_night

    override fun initUi(view: View, savedInstanceState: Bundle?): View {
        view.ffn_player_list.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(createDisplayManagers(), this, this)
        view.ffn_player_list.adapter = adapter
        return view
    }

    private lateinit var adapter: Adapter
    private lateinit var game: Game

    companion object{
        const val TAG = "FirstNightFragment"

        fun newInstance(game: Game): FirstNightFragment {
            val fragment = FirstNightFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ffn_button.background = ContextCompat.getDrawable(context!!, R.drawable.not_enabled_button)
    }

    private fun createDisplayManagers(): ArrayList<BasePlayerProvider> {
        val answer = ArrayList<BasePlayerProvider>()

        for (player in game.players){
            answer.add(BasePlayerProvider(player))
        }

        return answer
    }
}