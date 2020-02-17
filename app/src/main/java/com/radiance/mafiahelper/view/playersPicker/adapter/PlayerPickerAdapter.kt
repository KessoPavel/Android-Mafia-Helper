package com.radiance.mafiahelper.view.playersPicker.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radiance.mafiahelper.R
import com.radiance.mafiahelper.inflate
import com.radiance.mafiahelper.view.playersPicker.getStat
import kotlinx.android.synthetic.main.player_picker_item.view.*

class PlayerPickerAdapter(
    var items: ArrayList<PlayerItem>,
    private val clickListener: Holder.ClickListener,
    private val longClickListener: Holder.LongClickListener
) : RecyclerView.Adapter<PlayerPickerAdapter.Holder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val inflationView = parent.inflate(R.layout.player_picker_item, false)
        return Holder(inflationView, clickListener, longClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    class Holder(
        val view: View,
        val listener: ClickListener,
        val longClickListener: LongClickListener
    ) : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {
        private var item: PlayerItem? = null

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        fun bind(item: PlayerItem) {
            this.item = item

            view.ppi_name.text = item.player.name
            view.ppi_statistic.text = item.getStat()
            view.icon_title.text = item.player.name[0].toString().toUpperCase()

            if (item.selected) {
                view.icon_title.setTextColor(Color.parseColor("#C5E2C9"))
                view.icon.setColorFilter(Color.parseColor("#C5E2C9"))
            } else {
                view.icon_title.setTextColor(Color.parseColor("#969696"))
                view.icon.setColorFilter(Color.parseColor("#969696"))
            }


        }

        override fun onClick(v: View?) {
            item?.let {
                listener.onClick(it)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            item?.let {
                longClickListener.onLongClick(it)
            }
            return true
        }

        interface ClickListener {
            fun onClick(playerItem: PlayerItem)
        }

        interface LongClickListener {
            fun onLongClick(playerItem: PlayerItem)
        }
    }
}
