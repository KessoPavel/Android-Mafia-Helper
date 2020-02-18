package com.radiance.mafiahelper.view.night.night.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsvt.core.character.Character
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import kotlinx.android.synthetic.main.night_item.view.*

class NightAdapter(var characters: ArrayList<NightItem>, val listener: Holder.Listener): RecyclerView.Adapter<NightAdapter.Holder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val inflatedView = parent.inflate(R.layout.night_item, false)
        return Holder(inflatedView, listener)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        holder.bind(characters[position])
    }

    class Holder(
        val view: View,
        val listener: Listener
    ): RecyclerView.ViewHolder(view) {
        fun bind(item: NightItem) {
            view.n_name.text = item.character.player.name
            view.n_alias.text = item.character.name
            view.n_is_death.isChecked = item.isSelected

            view.n_is_death.setOnClickListener {
                listener.clickPlayer(item.character)
            }
        }

        interface Listener {
            fun clickPlayer(character: Character)
        }
    }
}