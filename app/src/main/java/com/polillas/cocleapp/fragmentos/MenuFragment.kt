package com.polillas.cocleapp.fragmentos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        view.lL_Paciente.setOnClickListener {
            listener?.onClickButton("mode")
        }
        view.iV_conf1.setOnClickListener {
            listener?.onClickButton("config")
        }
        view.lL_Terapista.setOnClickListener {
            listener?.onClickButton("terapista")
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun onClickButton(string: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() : MenuFragment{
            var frag = MenuFragment()
            return frag
        }
    }
}
