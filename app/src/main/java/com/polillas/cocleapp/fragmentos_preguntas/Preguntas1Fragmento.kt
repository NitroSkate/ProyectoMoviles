package com.polillas.cocleapp.fragmentos_preguntas

import android.content.Context
import android.media.MediaPlayer
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
import com.google.android.material.snackbar.Snackbar
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Viewmodel.GameViewModel
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.constants.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_practica1.*
import kotlinx.android.synthetic.main.fragment_preguntas1_fragmento.view.*


class Preguntas1Fragmento : Fragment() {
    private var cont : Int = 0
    private var contPREGUNTA: Int = 1
    private var total : Int =  0
    lateinit var respuesta: Sonido
     var puntaje: Int = 0
    private var start: Boolean = false
    private var actual: ArrayList<Sonido> = ArrayList()
    private var todos: ArrayList<Sonido> = ArrayList()
    private var pregunta: ArrayList<Sonido> = ArrayList()
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
            gameViewModel.setPunta(puntaje)
            gameViewModel.setCONT(cont)
        }
    //    Log.d("OUNTAE",gameViewModel.getCont().toString()+1)
        /*preguntaViewmodel.getAllSonidos().observe(this, Observer { sounds ->
            sounds?.let {
                it[]
            }
        })*/
        val view =  inflater.inflate(R.layout.fragment_preguntas1_fragmento, container, false).apply {
            var mediaPlayer: MediaPlayer = MediaPlayer()
            when(gameViewModel.getCont()){
                gameViewModel.getCont() ->{
                    Log.d("OUNTAE",gameViewModel.getCont().toString()+1)
                    //   Log.d("ONNNNNN",gameViewModel.geton().toString()+"1")

                    //   Log.d("ONNNNNN",gameViewModel.geton().toString()+"1")
                    preguntaViewmodel.getAllSonidos().observe(this@Preguntas1Fragmento, Observer { sounds ->
                        Log.d("ONNNNNN",gameViewModel.geton().toString()+"2")
                        sounds?.let {
                            Log.d("ONNNNNN",gameViewModel.geton().toString()+"2")
                            total = sounds.size
                            Log.i("osunds",sounds.toString())
                            if (gameViewModel.getRespuesta() == null){
                                Log.d("GER","GG")
                                sounds.forEach { sound->
                                    actual.add(sound)
                                    todos.add(sound)
                                    Log.d("TODOS",todos.size.toString())

                                }
                                gameViewModel.setActual(actual)
                                gameViewModel.setTodos(todos)
                            }


                            if(gameViewModel.getTodos().size > 4 ){
                                var whileint = 1

                                while (whileint <= 4){
                                    //   Log.d("ONNNNNN",gameViewModel.geton().toString())
                                    if (!gameViewModel.geton()){
                                        if (whileint == 1){
                                            Log.d("ONNNNNN",gameViewModel.geton().toString())
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


                                    if(whileint ==5){
                                        Log.d("ONNNNNN",gameViewModel.geton().toString())
                                        if (!gameViewModel.geton()){
                                            Log.d("GAMEON","HOLA")
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
                                        Log.d("ONNNNNN",gameViewModel.geton().toString())

                                        Log.d("TODOS",gameViewModel.getTodos().size.toString())







                                        //tv_pregunta.text = "Pregunta 1"

                                        bt_play.setOnClickListener {
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()

                                            }
                                            mediaPlayer.reset()
                                            mediaPlayer.setDataSource("https://projecto-moviles.herokuapp.com/api/preguntadown/"+  gameViewModel.getRespuesta()!!.rutaSonido)
                                            mediaPlayer.prepare()
                                            mediaPlayer.start()
                                        }
                                        Log.d("PREGUNTAs",gameViewModel.getPregunta().size.toString())
                                        Log.d("WHUT",cont.toString() + " " +todos.size)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[0]).rutaImagen ).into(one)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[1]).rutaImagen ).into(two)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[2]).rutaImagen ).into(three)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[3]).rutaImagen ).into(four)
                                        one.setOnClickListener {
                                        click(mediaPlayer,0)

                                        }
                                        two.setOnClickListener {
                                            click(mediaPlayer,1)

                                        }
                                        three.setOnClickListener {
                                            click(mediaPlayer,2)

                                        }
                                        four.setOnClickListener {
                                            click(mediaPlayer,3)

                                        }
                                    }

                                }
                                Log.d("PreguntaSIZE",pregunta.size.toString())
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
    fun check(int: Int):Boolean{
        if (gameViewModel.getPregunta().get(int) == gameViewModel.getRespuesta()){
            return true

        }else{
            return false
            Log.d("PREGUNTAs",pregunta.size.toString())
            Log.d("RESPUESTA","INCORRECTA")
        }

    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onNextQuestion(
            string: String,
            id:Int,
            puntaje: Int,
            start: Boolean
        )
    }
    fun click(mediaPlayer: MediaPlayer,pos: Int){
        mediaPlayer.stop()
        mediaPlayer.release()
        if (check(gameViewModel.getasc()[pos])){
            gameViewModel.setPunta(gameViewModel.getPunta()+1)
        }
        if(gameViewModel.getCont() >= gameViewModel.getTodos().size){
            gameViewModel.seton(false)
            listener?.onNextQuestion("finish", gameViewModel.getCont(),gameViewModel.getPunta(),true)

        }

        gameViewModel.seton(false)
        gameViewModel.setCONT(gameViewModel.getCont() + 1)
        listener?.onNextQuestion("next", gameViewModel.getCont(),gameViewModel.getPunta(),true)
    }

    companion object {

        @JvmStatic
        fun newInstance(num : Int,puntaje: Int,start:Boolean): Preguntas1Fragmento{
            var frag = Preguntas1Fragmento()
            frag.puntaje = puntaje
            frag.start = start


            frag.cont = num
            return frag
        }
    }
}
