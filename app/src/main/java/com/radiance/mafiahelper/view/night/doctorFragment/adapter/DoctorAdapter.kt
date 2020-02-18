package com.radiance.mafiahelper.view.night.doctorFragment.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsvt.core.character.Character
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import kotlinx.android.synthetic.main.doctor_choice_item.view.*

class DoctorAdapter(var characters: ArrayList<DoctorItem>, val listener: Holder.Listener): RecyclerView.Adapter<DoctorAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflatedView = parent.inflate(R.layout.doctor_choice_item, false)
        return Holder(inflatedView, listener)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(characters[position])
    }

    class Holder(val view: View, val listener: Listener): RecyclerView.ViewHolder(view) {

        fun bind(item: DoctorItem) {
            view.dc_name.text = item.character.player.name
            view.dc_alias.text = item.character.name
            view.dc_is_death.isChecked = item.isSelected

            view.dc_is_death.setOnClickListener {
                listener.characterClick(item.character)
            }
        }

        interface Listener {
            fun characterClick(character: Character)
        }
    }
}