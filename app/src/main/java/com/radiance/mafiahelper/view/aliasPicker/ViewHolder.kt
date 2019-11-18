package com.radiance.mafiahelper.view.aliasPicker

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.radiance.mafiahelper.player.PlayerHolder
import com.radiance.mafiahelper.view.adapter.Holder
import kotlinx.android.synthetic.main.alias_picker_item.view.*

class ViewHolder(view: View, private val viewModel: AliasPickerViewModel, private val touchHelper: ItemTouchHelper):
    Holder(view),
    TextWatcher,
    View.OnTouchListener
{
    private lateinit var holder: PlayerHolder

    override fun bind(holder: PlayerHolder) {
        this.holder = holder
        view.api_alias.setText(holder.pseudonym)
        view.api_name.text = holder.name

        view.api_alias.addTextChangedListener(this)
        view.api_drag.setOnTouchListener(this)
    }

    private fun clearAlias() {
        view.api_alias.setText("")
        viewModel.addAlias("", holder.player)
    }


    override fun afterTextChanged(s: Editable?) {
        viewModel.addAlias(s.toString(), holder.player)

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
}