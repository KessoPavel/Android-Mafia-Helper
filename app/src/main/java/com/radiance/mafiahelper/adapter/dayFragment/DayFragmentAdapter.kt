package com.radiance.mafiahelper.adapter.dayFragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.playerProvider.DayDisplayManager
import kotlinx.android.synthetic.main.fragment_day_item.view.*

class DayFragmentAdapter(
    private var players: ArrayList<DayDisplayManager>,
    private val listener: DayPlayerClickListener
):
        RecyclerView.Adapter<DayFragmentAdapter.PlayerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val inflationView = parent.inflate(R.layout.fragment_day_item, false)
        return PlayerHolder(inflationView, listener)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindManager(players[position])
    }

    fun setData(newPlayers: ArrayList<DayDisplayManager>){
        players = newPlayers
    }

    class PlayerHolder(val view: View, val listener: DayPlayerClickListener): RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(v: View?) {
            if (displayManager.isClickable){
                listener.click(displayManager.player)
            }
        }

        private lateinit var displayManager: DayDisplayManager

        fun bindManager(manager: DayDisplayManager){
            displayManager = manager

            view.fdi_number.text = displayManager.number
            view.fdi_name.text = displayManager.name
            view.fdi_pseudonym.text = displayManager.pseudonym
            view.fdi_status.text = displayManager.status

            if (displayManager.isClickable) {
                view.setOnClickListener(this)
            }
        }
    }
}