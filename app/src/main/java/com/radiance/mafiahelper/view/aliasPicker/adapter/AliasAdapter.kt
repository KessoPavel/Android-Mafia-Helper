package com.radiance.mafiahelper.view.aliasPicker.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bsvt.core.character.Character
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import kotlinx.android.synthetic.main.alias_picker_item.view.*

class AliasAdapter(var players: ArrayList<Character>, private val touchHelper: ItemTouchHelper, private val aliasListener: Holder.AliasListener): RecyclerView.Adapter<AliasAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflateView = parent.inflate(R.layout.alias_picker_item, false)
        return Holder(inflateView, touchHelper, aliasListener)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(players[position])
    }

    class Holder(val view: View,  private val touchHelper: ItemTouchHelper, private val listener: AliasListener):
        RecyclerView.ViewHolder(view), TextWatcher,
        View.OnTouchListener {
        lateinit var player: Character

        fun bind(player: Character) {
            this.player = player
            view.api_alias.setText(player.name)
            view.api_name.text = player.player.name

            view.api_alias.addTextChangedListener(this)
            view.api_drag.setOnTouchListener(this)
        }

        override fun afterTextChanged(s: Editable?) {
            listener.addAlias(s.toString(), player)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            view.performClick()
            touchHelper.startDrag(this)
            return false
        }

        interface AliasListener {
            fun addAlias(alias: String, player: Character)
        }
    }
}