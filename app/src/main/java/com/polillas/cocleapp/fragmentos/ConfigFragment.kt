package com.polillas.cocleapp.fragmentos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.fragment_config.view.*

/*
TODO Inicialmente era para mostrar la configuracion general del usuario, pero dejo de utilizarse este fragmento.
 */

class ConfigFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_config, container, false)
        view.iV_save.setOnClickListener {
            listener?.onClickButton("menu")
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
        // TODO: Update argument type and name
        fun onClickButton(string: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(): ConfigFragment{
            var fragment = ConfigFragment()
            return fragment
        }
    }
}
