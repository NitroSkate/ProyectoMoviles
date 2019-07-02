package com.polillas.cocleapp.fragmentos

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import kotlinx.android.synthetic.main.fragment_modo.view.*
import kotlinx.android.synthetic.main.multiple_info_windows.view.*

/*
TODO Fragmento encargado de refirigir al usuario hacia el modo desafio y modo practica. El boton de modo desafio, redigira a la
TODO pantalla de dificultad y el de practica directamente a los ejercicios.
 */


class ModoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var preguntaViewmodel: PreguntaViewmodel
    private var verifyQ = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        preguntaViewmodel.getAllPreguntas().observe(this, Observer { questions ->
            questions?.let {
                verifyQ = it.size
            }
        })
        val view =  inflater.inflate(R.layout.fragment_modo, container, false)
        view.iV_help_modo.setOnClickListener {
            var popup = inflater.inflate(R.layout.multiple_info_windows, null)
            var popupview = PopupWindow(popup, 700, 400, true)

            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                Text_info.text = "Seleccionar el modo desafio mostrara 3 dificultades para poder realizar el cuestionario. Seleccionar modo practica para realizar ejercicios de audición"
                this.setOnClickListener {
                    popupview.dismiss()
                }
            }
        }

        view.lL_Desafio.setOnClickListener{
            listener?.onOpcion(verifyQ, "exercise")
        }

        view.lL_Practica.setOnClickListener {
            listener?.onOpcion(verifyQ, "practice")
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener") as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onOpcion(verifyQ : Int, string: String)
    }

    companion object {
        fun newInstance() : ModoFragment{
            var fragmento = ModoFragment()
            return fragmento
        }
    }
}
