package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.PlayerHolder
import kotlinx.android.synthetic.main.fragment_first_night_item.view.*

class FirstNightHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener) {
    override fun bind(holder: PlayerHolder) {
        super.bind(holder)

        view.ffni_is_black.setOnCheckedChangeListener(null)
        view.ffni_is_doctor.setOnCheckedChangeListener(null)
        view.ffni_is_sheriff.setOnCheckedChangeListener(null)

        view.ffni_pseudonym.text = holder.pseudonym
        view.ffni_name.text = holder.name
        view.ffni_is_black.isChecked = holder.isBlack
        view.ffni_is_doctor.isChecked = holder.isDoctor
        view.ffni_is_sheriff.isChecked = holder.isSheriff

        view.ffni_is_sheriff.setOnCheckedChangeListener{
                compoundButton, isCheched ->
            listener.playerRoleChanged(holder, if (isCheched) Role.Sheriff else Role.Red)
        }

        view.ffni_is_doctor.setOnCheckedChangeListener{
                compoundButton, isCheched ->
            listener.playerRoleChanged(holder, if (isCheched) Role.Doctor else Role.Red)
        }

        view.ffni_is_black.setOnCheckedChangeListener{
                compoundButton, isCheched ->
            listener.playerRoleChanged(holder, if (isCheched) Role.Black else Role.Red)
        }
    }
}