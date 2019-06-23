package com.polillas.cocleapp.fragmentos_preguntas

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preguntas1_fragmento.view.*


class Preguntas1Fragmento : Fragment() {
    private var cont : Int = 0
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_preguntas1_fragmento, container, false).apply {
            when(cont){
                1 ->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 1"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 2)
                    }
                }
                2->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 2"
                    one.setOnClickListener {
                        listener?.onNextQuestion("finish", 0)
                    }
                }
            }

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
        fun onNextQuestion(string: String, id:Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(num : Int): Preguntas1Fragmento{
            var frag = Preguntas1Fragmento()
            frag.cont = num
            return frag
        }
    }
}
