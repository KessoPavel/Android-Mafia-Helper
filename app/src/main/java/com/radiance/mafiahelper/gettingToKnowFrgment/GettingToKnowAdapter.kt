package com.radiance.mafiahelper.gettingToKnowFrgment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.playerDisplayManager.GettingToKnownDisplayManager
import kotlinx.android.synthetic.main.fragment_getting_to_known_item.view.*
import javax.xml.transform.ErrorListener

class GettingToKnowAdapter(
    private var players: List<GettingToKnownDisplayManager>,
    private val listener: GettingToKnownItemListener
) : RecyclerView.Adapter<GettingToKnowAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GettingToKnowAdapter.PlayerHolder {
        val infrationView = parent.inflate(R.layout.fragment_getting_to_known_item, false)
        return PlayerHolder(infrationView, listener)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: GettingToKnowAdapter.PlayerHolder, position: Int) {
        holder.bindDisplayManager(players.get(position))
    }

    fun replaceData(newPlayers: List<GettingToKnownDisplayManager>){
        players = newPlayers
    }

    class PlayerHolder(v: View,val listener: GettingToKnownItemListener)
        : RecyclerView.ViewHolder(v), View.OnClickListener{

        private lateinit var displayManager: GettingToKnownDisplayManager
        private var view: View = v

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.clickToPlayer(player = displayManager.player)
        }

        fun bindDisplayManager(manager: GettingToKnownDisplayManager){
            displayManager = manager

            view.fgtki_name.text = displayManager.name
            view.fgtki_pseudonym.text = displayManager.pseudonym
            view.fgtki_number.text = displayManager.number
        }
    }
}