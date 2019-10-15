package com.radiance.mafiahelper.firstNightFragment

import android.content.Context
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
import androidx.core.content.ContextCompat
import com.radiance.mafiahelper.R
import kotlinx.android.synthetic.main.fragment_first_night.*


class FirstNightFragment: Fragment(), PlayerRoleChangeListener {
    private lateinit var adapter: FirstNightAdapter
    private lateinit var game: Game
    private lateinit var listener: StartDayListener

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is StartDayListener){
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement StartDayListener.")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ffn_button.background = ContextCompat.getDrawable(context!!, R.drawable.not_enabled_button)
    }

    override fun playerRoleChanged(player: Player, role: Role) {
        game.setPlayerRole(player, role)

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

    private fun createDisplayManagers(): ArrayList<FirstNightDisplayManager> {
        val answer = ArrayList<FirstNightDisplayManager>()

        for (player in game.players){
            answer.add(FirstNightDisplayManager(player))
        }

        return answer
    }
}