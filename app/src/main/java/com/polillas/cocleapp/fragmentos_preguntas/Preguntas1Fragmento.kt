package com.polillas.cocleapp.fragmentos_preguntas

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Viewmodel.GameViewModel
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.constants.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_preguntas1_fragmento.view.*
/*TODO archivo de Kotlin encargado de controlar la logica y el diseño de el pequeño juego de preguntas en su modo se
    Desafio , la logica del juego funciona gracias a que usando listas se agregan todas las preguntas, luego  se agregan al azar y
    se saca una respuesta, se escogen de 2 a 4 preguntas(Dependiendo de la dificultad y procurando que no se repitan dos opciones)
     de estas preguntas para asi obtener las opciones que se muestran en la pregunta, coincidiendo el numero de botones con el numero de opciones,
     al final se corrobora la respuesta, se lleva el puntaje y el contador que nos dice el numero de preguntas que se lleva
     hasta el momento
TODO
* */


class Preguntas1Fragmento : Fragment() {
    private var cont : Int = 0
    private var total : Int =  0
     var puntaje: Int = 0
    private var dificultad: Int= 0
    private var start: Boolean = false
    private var actual: ArrayList<Sonido> = ArrayList()
    private var todos: ArrayList<Sonido> = ArrayList()
    private var pregunta: ArrayList<Sonido> = ArrayList()
    private var listener: OnFragmentInteractionListener? = null
    private var arrayImageVe: ArrayList<ImageView> = ArrayList()
    private lateinit var preguntaViewmodel : PreguntaViewmodel
    private lateinit var gameViewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        if (start){
            gameViewModel.setDificulty(dificultad)
            gameViewModel.setPunta(puntaje)
            gameViewModel.setCONT(cont)
        }

        val view =  inflater.inflate(R.layout.fragment_preguntas1_fragmento, container, false).apply {
            if(gameViewModel.getDificulty() == 3){
                four.visibility = View.GONE
            }
            else if(gameViewModel.getDificulty() ==2){
                four.visibility = View.GONE
                three.visibility = View.GONE
            }
            var mediaPlayer: MediaPlayer = MediaPlayer()
            arrayImageVe.add(one)
            arrayImageVe.add(two)
            arrayImageVe.add(three)
            arrayImageVe.add(four)

            when(gameViewModel.getCont()){
                gameViewModel.getCont() ->{

                    preguntaViewmodel.getAllSonidos().observe(this@Preguntas1Fragmento, Observer { sounds ->

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

                                while (whileint <= gameViewModel.getDificulty()){

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
                                            Log.d("BREAKPOINT1",gameViewModel.getTodos().size.toString() +" "+ rnds.toString())
                                            if(gameViewModel.getTodos().get(rnds)==gameViewModel.getRespuesta()|| pregunta.contains(gameViewModel.getTodos().get(rnds))){
                                                Log.d("NO HAY","PENAL")
                                            }else{
                                                pregunta.add(gameViewModel.getTodos().get(rnds))


                                                whileint++
                                            }

                                        }
                                    }else{
                                        whileint++
                                    }


                                    if(whileint > gameViewModel.getDificulty()){

                                        if (!gameViewModel.geton()){

                                            gameViewModel.setPregunta(pregunta)
                                            val asc = Array(gameViewModel.getDificulty()) { i -> (5) }

                                            var whileint = 1


                                            while(whileint <=gameViewModel.getDificulty()){
                                                val rnds = (0..gameViewModel.getDificulty()-1).random()
                                                if(!asc.contains(rnds)){
                                                    asc[whileint-1] = rnds


                                                    whileint++

                                                }

                                            }
                                            gameViewModel.setasc(asc)
                                            gameViewModel.seton(true)
                                        }


                                       var conta = 1

                                        while(conta <= gameViewModel.getDificulty()){

                                            arrayImageVe.get(conta-1).setOnClickListener {


                                                if (it.id== R.id.one){
                                                    click(mediaPlayer,0,gameViewModel.getDificulty())
                                                }
                                                if (it.id== R.id.two){
                                                    click(mediaPlayer,1,gameViewModel.getDificulty())
                                                }
                                                if (it.id== R.id.three){
                                                    click(mediaPlayer,2,gameViewModel.getDificulty())
                                                }
                                                if (it.id== R.id.four){
                                                    click(mediaPlayer,3,gameViewModel.getDificulty())
                                                }



                                            }

                                            Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[conta-1]).rutaImagen ).into(arrayImageVe.get(conta-1))
                                            conta++

                                        }

                                        bt_play.setOnClickListener {
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()

                                            }
                                            mediaPlayer.reset()
                                            mediaPlayer.setDataSource("https://projecto-moviles.herokuapp.com/api/preguntadown/"+  gameViewModel.getRespuesta()!!.rutaSonido)
                                            mediaPlayer.prepare()
                                            mediaPlayer.start()
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
    fun check(int: Int):Boolean{
        if (gameViewModel.getPregunta().get(int) == gameViewModel.getRespuesta()){
            return true

        }else{
            return false

        }

    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onNextQuestion(
            string: String,
            id:Int,
            puntaje: Int,
            start: Boolean,
            dificultad: Int
        )
    }
    fun click(mediaPlayer: MediaPlayer,pos: Int,dificultad: Int){
        mediaPlayer.stop()
        mediaPlayer.release()

        if (check(gameViewModel.getasc()[pos])){
            gameViewModel.setPunta(gameViewModel.getPunta()+1)
            view?.let { Snackbar.make(it,"Respuesta Correcta", Snackbar.LENGTH_SHORT).show() }
        }else{
            view?.let { Snackbar.make(it,"Respuesta Incorrecta",Snackbar.LENGTH_SHORT).show() }
        }
        if(gameViewModel.getCont() >= AppConstants.MAX_PREGUNTAS){
            gameViewModel.seton(false)
            listener?.onNextQuestion("finish", gameViewModel.getCont(),gameViewModel.getPunta(),true,dificultad)

        }else{
            gameViewModel.seton(false)
            gameViewModel.setCONT(gameViewModel.getCont() + 1)
            listener?.onNextQuestion("next", gameViewModel.getCont(),gameViewModel.getPunta(),true,dificultad)

        }


    }

    companion object {

        @JvmStatic
        fun newInstance(num : Int,puntaje: Int,start:Boolean,dificultad: Int): Preguntas1Fragmento{
            var frag = Preguntas1Fragmento()
            frag.puntaje = puntaje
            frag.start = start
            frag.dificultad = dificultad



            frag.cont = num
            return frag
        }
    }
}
