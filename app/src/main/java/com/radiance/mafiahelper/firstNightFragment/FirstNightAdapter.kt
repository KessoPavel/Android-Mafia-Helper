package com.radiance.mafiahelper.firstNightFragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.playerProvider.FirstNightDisplayManager
import kotlinx.android.synthetic.main.fragment_first_night_item.view.*

class FirstNightAdapter(
    private var players: ArrayList<FirstNightDisplayManager>,
    private val listener: PlayerRoleChangeListener
)
    : RecyclerView.Adapter<FirstNightAdapter.PlayerHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerHolder {
        val inflationView = parent.inflate(R.layout.fragment_first_night_item, false)
        return PlayerHolder(inflationView, listener)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindManager(players[position])
    }

    fun setData(newPlayers: ArrayList<FirstNightDisplayManager>) {
        players = newPlayers
    }

    class PlayerHolder(val view: View, val listener: PlayerRoleChangeListener): RecyclerView.ViewHolder(view) {
        private lateinit var displayManager: FirstNightDisplayManager

        fun bindManager(manager: FirstNightDisplayManager){
            displayManager = manager

            view.ffni_is_black.setOnCheckedChangeListener(null)
            view.ffni_is_doctor.setOnCheckedChangeListener(null)
            view.ffni_is_sheriff.setOnCheckedChangeListener(null)

            view.ffni_pseudonym.text = displayManager.pseudonym
            view.ffni_name.text = displayManager.name
            view.ffni_is_black.isChecked = displayManager.isBlack
            view.ffni_is_doctor.isChecked = displayManager.isDoctor
            view.ffni_is_sheriff.isChecked = displayManager.isSheriff

            view.ffni_is_sheriff.setOnCheckedChangeListener{
                compoundButton, isCheched ->
                    listener.playerRoleChanged(displayManager.player, if (isCheched) Role.Sheriff else Role.Red)
            }

            view.ffni_is_doctor.setOnCheckedChangeListener{
                    compoundButton, isCheched ->
                listener.playerRoleChanged(displayManager.player, if (isCheched) Role.Doctor else Role.Red)
            }

            view.ffni_is_black.setOnCheckedChangeListener{
                    compoundButton, isCheched ->
                listener.playerRoleChanged(displayManager.player, if (isCheched) Role.Black else Role.Red)
            }
        }
    }

}