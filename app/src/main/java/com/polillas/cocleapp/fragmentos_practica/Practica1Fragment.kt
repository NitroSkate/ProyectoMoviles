package com.polillas.cocleapp.fragmentos_practica

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Viewmodel.GameViewModel
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.constants.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_practica1.*
import kotlinx.android.synthetic.main.popup_practice.view.*


/*TODO archivo de Kotlin encargado de controlar la logica y el diseño de el pequeño juego de preguntas en su modo se
    Practica , la logica del juego funciona gracias a que usando listas se agregan todas las preguntas, luego  se agregan al azar y
    se saca una respuesta, se escogen 4 preguntas(procurando que no se repitan dos opciones) de estas preguntas para asi obtener las
    opciones que se muestran en la pregunta, al final se corrobora la respuesta, se lleva el contador que nos dice el numero de
    preguntas que se lleva hasta el momento
TODO
* */
class Practica1Fragment : Fragment()  {
    private var cont : Int = 0

    private var total : Int =  0

    private var actual: ArrayList<Sonido> = ArrayList()
    private var todos: ArrayList<Sonido> = ArrayList()
    private var pregunta: ArrayList<Sonido> = ArrayList()
    private var start: Boolean = false
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var preguntaViewmodel : PreguntaViewmodel
    private lateinit var gameViewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        if (start){

            gameViewModel.setCONT(cont)
        }
        val view =  inflater.inflate(R.layout.fragment_practica1, container, false).apply {
            var mediaPlayer: MediaPlayer = MediaPlayer()
            when(gameViewModel.getCont()){
                gameViewModel.getCont() ->{

                    preguntaViewmodel.getAllSonidos().observe(this@Practica1Fragment, Observer { sounds ->
                        sounds?.let {
                            total = sounds.size

                            if (gameViewModel.getRespuesta() == null){

                                sounds.forEach { sound->
                                    actual.add(sound)
                                    todos.add(sound)


                                }
                                gameViewModel.setActual(actual)
                                gameViewModel.setTodos(todos)
                            }


                            if(gameViewModel.getTodos().size >= 4 ){
                                var whileint = 1

                                while (whileint <= 4){

                                    if (!gameViewModel.geton()){
                                        if (whileint == 1){

                                            val rnds = (0..gameViewModel.getTodos().size-1).random()
                                            if (gameViewModel.getActual().contains(gameViewModel.getTodos().get(rnds))){

                                                pregunta.add(gameViewModel.getTodos().get(rnds))
                                                gameViewModel.setRespuesta(gameViewModel.getTodos().get(rnds))
                                                gameViewModel.getActual().remove(gameViewModel.getTodos().get(rnds))
                                                whileint++
                                            }else{
                                                Log.d("NO HAY","PENAL")
                                            }


                                        }else{

                                            val rnds = (0..gameViewModel.getTodos().size-1).random()
                                            if(gameViewModel.getTodos().get(rnds)==gameViewModel.getRespuesta()|| pregunta.contains(gameViewModel.getTodos().get(rnds))){

                                            }else{
                                                pregunta.add(gameViewModel.getTodos().get(rnds))


                                                whileint++
                                            }

                                        }
                                    }else{
                                        whileint++
                                    }

                                    if(whileint ==5){
                                        if (!gameViewModel.geton()){

                                            gameViewModel.setPregunta(pregunta)
                                            val asc = Array(4) { i -> (5) }

                                            var whileint = 1


                                            while(whileint <=4){
                                                val rnds = (0..3).random()
                                                if(!asc.contains(rnds)){
                                                    asc[whileint-1] = rnds

                                                    Log.d("ANS",whileint.toString()+" " + rnds)
                                                    whileint++

                                                }

                                            }
                                            gameViewModel.setasc(asc)
                                            gameViewModel.seton(true)
                                        }



                                        bt_play_p.setOnClickListener {
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()

                                            }
                                            mediaPlayer.reset()
                                            mediaPlayer.setDataSource("https://projecto-moviles.herokuapp.com/api/preguntadown/"+  gameViewModel.getRespuesta()!!.rutaSonido)
                                            mediaPlayer.prepare()
                                            mediaPlayer.start()
                                        }

                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[0]).rutaImagen ).into(one_p)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[1]).rutaImagen ).into(two_p)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[2]).rutaImagen ).into(three_p)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[3]).rutaImagen ).into(four_p)
                                        one_p.setOnClickListener {

                                            click(mediaPlayer,this,0)

                                        }
                                        two_p.setOnClickListener {
                                            click(mediaPlayer,this,1)
                                        }
                                        three_p.setOnClickListener {
                                            click(mediaPlayer,this,2)
                                        }
                                        four_p.setOnClickListener {
                                            click(mediaPlayer,this,3)
                                        }
                                    }

                                }

                            }

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

    fun popup(context:View){
        var popup = LayoutInflater.from(context.context).inflate(R.layout.popup_practice, null)
        var popupview = PopupWindow(popup, 500, 700, true)
        popupview.showAtLocation(context, Gravity.CENTER,0,0)
        popup.apply {
            si.setOnClickListener {
                popupview.dismiss()
                listener?.onNextQuestion("si", 0,true)

            }
            no.setOnClickListener {
                popupview.dismiss()
                listener?.onNextQuestion("no", 0,true)

            }
        }
    }

    fun check(int: Int):Boolean{
        if (gameViewModel.getPregunta().get(int) == gameViewModel.getRespuesta()){
            return true

        }else{
            return false

        }

    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onNextQuestion(string: String, id:Int,start:Boolean)
    }
    fun click(mediaPlayer: MediaPlayer,view: View,pos : Int){

        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
        }

        if(gameViewModel.getCont() >= AppConstants.MAX_PREGUNTAS){

            mediaPlayer.release()
            popup(view)

        }else{
            if(check(gameViewModel.getasc()[pos])){
                mediaPlayer.release()

                gameViewModel.setCONT(gameViewModel.getCont()+1)
                gameViewModel.seton(false)
                Snackbar.make(view,"Respuesta Correcta",Snackbar.LENGTH_SHORT).show()
                listener?.onNextQuestion("next", gameViewModel.getCont(),true)
            }else{
                view?.let { it1 -> make(it1,"Intentalo Otra Vez", LENGTH_SHORT).show() }
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(num : Int,start: Boolean): Practica1Fragment{
            var frag = Practica1Fragment()
            frag.start = start
            frag.cont = num
            return frag
        }
    }
}
