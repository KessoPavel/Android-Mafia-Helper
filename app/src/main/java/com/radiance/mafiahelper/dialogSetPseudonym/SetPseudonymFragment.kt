package com.radiance.mafiahelper.dialogSetPseudonym

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.radiance.mafiahelper.R
import kotlinx.android.synthetic.main.fragment_set_pseudionym.*

class SetPseudonymFragment: Fragment() {
    private lateinit var listener: SetPseudonymListener
    private lateinit var prefab: String

    companion object{
        fun newInstance(listener: SetPseudonymListener, pseudonym: String): SetPseudonymFragment {
            val fragment = SetPseudonymFragment()
            fragment.listener = listener
            fragment.prefab = pseudonym
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_pseudionym, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fsp_pseudonym.setText(this.prefab)

        fsp_main.setOnClickListener{
            Log.d("SetPseudonymFragment", "Click")
        }

        fsp__ok.setOnClickListener{
            fsp_pseudonym.clearFocus()

            val pseudonym = fsp_pseudonym.text.toString()

            if (pseudonym == "")
                return@setOnClickListener

            listener.playerGotAnPseudonym(pseudonym)
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        fsp_exit.setOnClickListener{_ ->
            fsp_pseudonym.clearFocus()
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }
}