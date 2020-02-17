package com.radiance.mafiahelper.view.firstNight.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsvt.core.character.Character
import com.bsvt.core.character.role.Role
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import kotlinx.android.synthetic.main.first_night_item.view.*

class FirstNightAdapter(var characters: ArrayList<Character>, val listener: Holder.RoleListener): RecyclerView.Adapter<FirstNightAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflateView = parent.inflate(R.layout.first_night_item, false)
        return Holder(inflateView, listener)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(characters[position])
    }

    class Holder(val view: View, val listener: RoleListener): RecyclerView.ViewHolder(view) {
        lateinit var character: Character

        fun bind(character: Character) {
            this.character = character

            view.fn_name.text = character.player.name
            view.fn_alias.text = character.name
            view.fn_doctor.isChecked = character.role == Role.Doctor
            view.fn_sheriff.isChecked = character.role == Role.Sheriff
            view.fn_black.isChecked = character.role == Role.Black

            view.fn_doctor.setOnClickListener { v: View? ->
                listener.setRole(Role.Doctor, character)
            }
            view.fn_sheriff.setOnClickListener { v: View? ->
                listener.setRole(Role.Sheriff, character)
            }
            view.fn_black.setOnClickListener { v: View? ->
                listener.setRole(Role.Black, character)
            }
        }

        interface RoleListener{
            fun setRole(role: Role, character: Character)
        }
    }
}
