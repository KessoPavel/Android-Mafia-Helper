package com.radiance.mafiahelper.firstNightFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiance.mafiahelper.game.Game
import com.radiance.mafiahelper.player.Player
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.playerDisplayManager.FirstNightDisplayManager
import kotlinx.android.synthetic.main.fragment_first_night.view.*
import android.os.Handler


class FirstNightFragment: Fragment(), PlayerRoleChangeListener {
    private lateinit var adapter: FirstNightAdapter
    private lateinit var game: Game
    //private lateinit var listener: StartDayListener

    companion object{
        const val TAG = "FirstNightFragment"

        fun newInstance(game: Game): FirstNightFragment {
            val fragment = FirstNightFragment()
            fragment.game = game
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.radiance.mafiahelper.R.layout.fragment_first_night, container, false)
        view.ffn_player_list.layoutManager = LinearLayoutManager(context)
        adapter = FirstNightAdapter(createDisplayManagers(), this)
        view.ffn_player_list.adapter = adapter
        return view
    }

    override fun playerRoleChanged(player: Player, role: Role) {
        player.role = role
        adapter.setData(createDisplayManagers())


        Handler().post {
            adapter.notifyDataSetChanged()
        }

    }

    private fun createDisplayManagers(): ArrayList<FirstNightDisplayManager> {
        val answer = ArrayList<FirstNightDisplayManager>()

        for (player in game.players){
            answer.add(FirstNightDisplayManager(player))
        }

        return answer
    }
}