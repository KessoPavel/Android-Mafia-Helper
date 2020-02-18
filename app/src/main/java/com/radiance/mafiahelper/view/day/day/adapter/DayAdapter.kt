package com.radiance.mafiahelper.view.day.day.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsvt.core.character.Character
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import kotlinx.android.synthetic.main.day_item.view.*

class DayAdapter(var characters: ArrayList<NominationItem>, private val listener: Holder.Listener) :
    RecyclerView.Adapter<DayAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflateView = parent.inflate(R.layout.day_item, false)
        return Holder(inflateView, listener)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(characters[position])
    }

    class Holder(val view: View, val listener: Listener) : RecyclerView.ViewHolder(view) {

        fun bind(item: NominationItem) {
            view.d_alias.text = item.character.name
            view.d_name.text = item.character.player.name

            view.d_in_vote.isChecked =  item.inNomination
            view.d_in_vote.setOnClickListener { listener.characterClick(character = item.character) }
        }

        interface Listener {
            fun characterClick(character: Character)
        }
    }
}