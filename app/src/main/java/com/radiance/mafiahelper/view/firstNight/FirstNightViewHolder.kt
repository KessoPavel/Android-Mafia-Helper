package com.radiance.mafiahelper.view.firstNight

import android.view.View
import android.widget.Adapter
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.player.Role
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.first_night_item.view.*

class FirstNightViewHolder(view: View, private val viewModel: FirstNightViewModel): Holder(view) {
    private lateinit var holder: PlayerHolder

    override fun bind(holder: PlayerHolder) {
        this.holder = holder
        view.fn_name.text = holder.name
        view.fn_alias.text = holder.pseudonym
        view.fn_doctor.isChecked = holder.isDoctor
        view.fn_sheriff.isChecked = holder.isSheriff
        view.fn_black.isChecked = holder.isBlack

        view.fn_doctor.setOnClickListener { v: View? ->
            viewModel.changeRole(this.holder.player, Role.Doctor)
        }
        view.fn_sheriff.setOnClickListener { v: View? ->
            viewModel.changeRole(this.holder.player, Role.Sheriff)
        }
        view.fn_black.setOnClickListener { v: View? ->
            viewModel.changeRole(this.holder.player, Role.Black)
        }
    }
}