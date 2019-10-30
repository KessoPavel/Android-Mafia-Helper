package com.radiance.mafiahelper.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.game.GameListener

abstract class GameFragment: Fragment() {
    protected lateinit var listener: GameListener
    abstract val layoutId: Int
    val fragmentTag: String
        get() = this.javaClass.name

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is GameListener){
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement GameListener.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        return initUi(view = view, savedInstanceState = savedInstanceState)
    }

    abstract fun initUi(view: View, savedInstanceState: Bundle?): View;
}