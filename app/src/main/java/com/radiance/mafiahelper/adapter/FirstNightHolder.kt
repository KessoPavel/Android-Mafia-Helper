package com.radiance.mafiahelper.adapter

import android.view.View
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.player.playerProvider.BasePlayerProvider
import kotlinx.android.synthetic.main.fragment_first_night_item.view.*

class FirstNightHolder(view: View, listener: Adapter.HolderListener): BaseHolder(view, listener) {
    override fun bind(provider: BasePlayerProvider) {
        super.bind(provider)

        view.ffni_is_black.setOnCheckedChangeListener(null)
        view.ffni_is_doctor.setOnCheckedChangeListener(null)
        view.ffni_is_sheriff.setOnCheckedChangeListener(null)

        view.ffni_pseudonym.text = provider.pseudonym
        view.ffni_name.text = provider.name
        view.ffni_is_black.isChecked = provider.isBlack
        view.ffni_is_doctor.isChecked = provider.isDoctor
        view.ffni_is_sheriff.isChecked = provider.isSheriff

        view.ffni_is_sheriff.setOnCheckedChangeListener{
                compoundButton, isCheched ->
            listener.playerRoleChanged(provider, if (isCheched) Role.Sheriff else Role.Red)
        }

        view.ffni_is_doctor.setOnCheckedChangeListener{
                compoundButton, isCheched ->
            listener.playerRoleChanged(provider, if (isCheched) Role.Doctor else Role.Red)
        }

        view.ffni_is_black.setOnCheckedChangeListener{
                compoundButton, isCheched ->
            listener.playerRoleChanged(provider, if (isCheched) Role.Black else Role.Red)
        }
    }
}