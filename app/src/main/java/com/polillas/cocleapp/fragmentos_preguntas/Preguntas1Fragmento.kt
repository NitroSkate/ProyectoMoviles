package com.polillas.cocleapp.fragmentos_preguntas

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.constants.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preguntas1_fragmento.view.*


class Preguntas1Fragmento : Fragment() {
    private var cont : Int = 0
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var preguntaViewmodel : PreguntaViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        /*preguntaViewmodel.getAllSonidos().observe(this, Observer { sounds ->
            sounds?.let {
                it[]
            }
        })*/
        val view =  inflater.inflate(R.layout.fragment_preguntas1_fragmento, container, false).apply {
            when(cont){
                1 ->{
                    preguntaViewmodel.getAllSonidos().observe(this@Preguntas1Fragmento, Observer { sounds ->
                        sounds?.let {
                          tv_pregunta.text = it[cont-1].rutaImagen
                        }
                    })
                    Picasso.get()
                        .load(AppConstants.BASE_URL)
                        .into(image1)
                    //tv_pregunta.text = "Pregunta 1"
                    bt_play.setOnClickListener {

                    }
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 2)
                    }
                }
                2->{
                    Picasso.get()
                        .load("https://regmedia.co.uk/2018/10/01/ms-dos_logo.png")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 2"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 3)
                    }
                }
                3->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 3"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 4)
                    }
                }
                4->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 4"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 5)
                    }
                }
                5->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 5"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 6)
                    }
                }
                6->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 6"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 7)
                    }
                }
                7->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 7"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 8)
                    }
                }
                8->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 8"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 9)
                    }
                }
                9->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 9"
                    one.setOnClickListener {
                        listener?.onNextQuestion("next", 10)
                    }
                }
                10->{
                    Picasso.get()
                        .load("https://es.wikipedia.org/wiki/Calocitta_colliei#/media/Archivo:Calocitta_collieiPCCA20051227-1964B.jpg")
                        .into(image1)
                    tv_pregunta.text = "Pregunta 10"
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
