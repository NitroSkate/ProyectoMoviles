package com.polillas.cocleapp.fragmentos_preguntas

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.constants.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preguntas1_fragmento.view.*


class Preguntas1Fragmento : Fragment() {
    private var cont : Int = 0
    private var contPREGUNTA: Int = 1
    private var total : Int =  0
    lateinit var respuesta: Sonido
    private var actual: ArrayList<Sonido> = ArrayList()
    private var todos: ArrayList<Sonido> = ArrayList()
    private var pregunta: ArrayList<Sonido> = ArrayList()
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
                cont ->{
                    Log.d("TODOS#",todos.size.toString())
                    preguntaViewmodel.getAllSonidos().observe(this@Preguntas1Fragmento, Observer { sounds ->
                        sounds?.let {
                            total = sounds.size
                            Log.i("osunds",sounds.toString())
                            sounds.forEach { sound->
                                actual.add(sound)
                                todos.add(sound)
                                Log.d("TODOS",todos.size.toString())

                            }
                            if(cont < todos.size){
                           //     tv_pregunta.text = it[cont-1].rutaImagen
                            }

                            if(todos.size > 4 ){
                                var whileint = 1

                                while (whileint <= 4){
                                    if (whileint == 1){

                                        val rnds = (0..todos.size-1).random()
                                        if (actual.contains(todos.get(rnds))){

                                            pregunta.add(todos.get(rnds))
                                            respuesta = todos.get(rnds)
                                            actual.remove(todos.get(rnds))
                                            whileint++
                                        }else{
                                            Log.d("NO HAY","PENAL")
                                        }


                                    }else{

                                        val rnds = (0..todos.size-1).random()
                                        Log.d("BREAKPOINT1",todos.size.toString() +" "+ rnds.toString())
                                        if(todos.get(rnds)==respuesta || pregunta.contains(todos.get(rnds))){
                                            Log.d("NO HAY","PENAL")
                                        }else{
                                            pregunta.add(todos.get(rnds))


                                            whileint++
                                        }

                                    }
                                    if(whileint ==5){
                                        Log.d("TODOS",todos.size.toString())







                                        val asc = Array(4) { i -> (5) }

                                        var whileint = 1


                                        while(whileint <=4){
                                            val rnds = (0..3).random()
                                            if(!asc.contains(rnds)){
                                                asc[whileint-1] = rnds

                                                Log.d("ANS",whileint.toString()+" " + rnds)
                                                whileint++

                                            }else{

                                            }

                                        }



                                        //tv_pregunta.text = "Pregunta 1"

                                        bt_play.setOnClickListener {

                                        }
                                        Log.d("PREGUNTAs",pregunta.size.toString())
                                        Log.d("WHUT",cont.toString() + " " +todos.size)
                                        Picasso.get().load(AppConstants.BASE_URL + pregunta.get(0).rutaImagen ).into(one)
                                        Picasso.get().load(AppConstants.BASE_URL + pregunta.get(1).rutaImagen ).into(two)
                                        Picasso.get().load(AppConstants.BASE_URL + pregunta.get(2).rutaImagen ).into(three)
                                        Picasso.get().load(AppConstants.BASE_URL + pregunta.get(3).rutaImagen ).into(four)
                                        one.setOnClickListener {
                                            check(asc[0])
                                            if(cont >= todos.size){
                                                listener?.onNextQuestion("finish", cont)
                                            }
                                            cont++
                                            listener?.onNextQuestion("next", cont)
                                        }
                                        two.setOnClickListener {
                                            check(asc[1])
                                            if(cont >= todos.size){
                                                listener?.onNextQuestion("finish", cont)
                                            }
                                            cont++
                                            listener?.onNextQuestion("next", cont)
                                        }
                                        three.setOnClickListener {
                                            check(asc[2])
                                            if(cont >= todos.size){
                                                listener?.onNextQuestion("finish", cont)
                                            }
                                            cont++
                                            listener?.onNextQuestion("next", cont)
                                        }
                                        four.setOnClickListener {
                                            check(asc[3])
                                            if(cont >= todos.size){
                                                listener?.onNextQuestion("finish", cont)
                                            }
                                            cont++
                                            listener?.onNextQuestion("next", cont)
                                        }
                                    }

                                }
                                Log.d("PreguntaSIZE",pregunta.size.toString())
                            }
                            if(cont< todos.size ){

                            }
                            // tv_pregunta.text = it[cont-1].rutaImagen
                        }
                    })

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
    fun check(int: Int){
        if (pregunta.get(int) == respuesta){
            Log.d("PREGUNTAs",pregunta.size.toString())
            Log.d("RESPUESTA","CORRECTA")
        }else{
            Log.d("PREGUNTAs",pregunta.size.toString())
            Log.d("RESPUESTA","INCORRECTA")
        }

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
