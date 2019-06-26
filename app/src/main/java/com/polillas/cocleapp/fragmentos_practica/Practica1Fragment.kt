package com.polillas.cocleapp.fragmentos_practica

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import com.google.common.base.Strings
import com.polillas.cocleapp.MainActivity
//import com.example.cocleapp.R

import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Sonido
import com.polillas.cocleapp.Room.Viewmodel.GameViewModel
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.polillas.cocleapp.constants.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_practica1.*
import kotlinx.android.synthetic.main.fragment_preguntas1_fragmento.view.*
import kotlinx.android.synthetic.main.popup_practice.view.*


class Practica1Fragment : Fragment()  {
    private var cont : Int = 0
    private var contPREGUNTA: Int = 1
    private var total : Int =  0
    lateinit var respuesta: Sonido
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
        /*preguntaViewmodel.getAllSonidos().observe(this, Observer { sounds ->
            sounds?.let {
                it[]
            }
        })*/
        val view =  inflater.inflate(R.layout.fragment_practica1, container, false).apply {
            var mediaPlayer: MediaPlayer = MediaPlayer()
            when(cont){
                cont ->{
                    Log.d("GH",gameViewModel.getRespuesta().toString())
                    Log.d("GH",gameViewModel.getPregunta().toString())
                    Log.d("GH",gameViewModel.geton().toString())
                    Log.d("TODOS#",todos.size.toString())
                    preguntaViewmodel.getAllSonidos().observe(this@Practica1Fragment, Observer { sounds ->
                        sounds?.let {
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
                                    Log.d("HELLOS","HEERE")
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


                                    if(whileint ==5){
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

                                        bt_play_p.setOnClickListener {
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()
                                                mediaPlayer.reset()
                                            }
                                            mediaPlayer.setDataSource("https://projecto-moviles.herokuapp.com/api/preguntadown/"+  gameViewModel.getRespuesta()!!.rutaSonido)
                                            mediaPlayer.prepare()
                                            mediaPlayer.start()
                                        }
                                        Log.d("PREGUNTAs",gameViewModel.getPregunta().size.toString())
                                        Log.d("WHUT",cont.toString() + " " +todos.size)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[0]).rutaImagen ).into(one_p)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[1]).rutaImagen ).into(two_p)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[2]).rutaImagen ).into(three_p)
                                        Picasso.get().load(AppConstants.BASE_URL + "api/preguntadown/" + gameViewModel.getPregunta().get(gameViewModel.getasc()[3]).rutaImagen ).into(four_p)
                                        one_p.setOnClickListener {
                                            //mediaPlayer.stop()
                                            //mediaPlayer.release()
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()
                                            }

                                            if(cont >= gameViewModel.getTodos().size){
                                                mediaPlayer.release()
                                                popup(this)
                                                //listener?.onNextQuestion("finish", cont)
                                            }
                                            if(check(gameViewModel.getasc()[0])){
                                                mediaPlayer.release()
                                                cont++
                                                gameViewModel.seton(false)
                                                listener?.onNextQuestion("next", cont)
                                            }else{
                                                view?.let { it1 -> make(it1,"Intentalo Otra Vez", LENGTH_SHORT).show() }
                                            }

                                        }
                                        two_p.setOnClickListener {
                                            //mediaPlayer.stop()
                                            //mediaPlayer.release()
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()
                                            }

                                            if(cont >= gameViewModel.getTodos().size){
                                                mediaPlayer.release()
                                                popup(this)
                                                //listener?.onNextQuestion("finish", cont)
                                            }
                                            if(check(gameViewModel.getasc()[1])){
                                                mediaPlayer.release()
                                                cont++
                                                gameViewModel.seton(false)
                                                listener?.onNextQuestion("next", cont)
                                            }else{
                                                view?.let { it1 -> make(it1,"Intentalo Otra Vez", LENGTH_SHORT).show() }
                                            }}
                                        three_p.setOnClickListener {
                                            //mediaPlayer.stop()
                                            //mediaPlayer.release()
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()
                                            }

                                            if(cont >= gameViewModel.getTodos().size){
                                                mediaPlayer.release()
                                                popup(this)
                                                //listener?.onNextQuestion("finish", cont)
                                            }
                                            if(check(gameViewModel.getasc()[2])){
                                                mediaPlayer.release()
                                                cont++
                                                gameViewModel.seton(false)
                                                listener?.onNextQuestion("next", cont)
                                        }else{
                                                view?.let { it1 -> make(it1,"Intentalo Otra Vez", LENGTH_SHORT).show() }
                                            }}
                                        four_p.setOnClickListener {
                                            //mediaPlayer.stop()
                                            //mediaPlayer.release()
                                            if(mediaPlayer.isPlaying){
                                                mediaPlayer.stop()
                                            }

                                            if(cont >= gameViewModel.getTodos().size){
                                                mediaPlayer.release()
                                                popup(this)
                                                //listener?.onNextQuestion("finish", cont)
                                            }
                                            if(check(gameViewModel.getasc()[3])){
                                                mediaPlayer.release()
                                                cont++
                                                gameViewModel.seton(false)
                                                listener?.onNextQuestion("next", cont)
                                            }else{
                                                view?.let { it1 -> make(it1,"Intentalo Otra Vez", LENGTH_SHORT).show() }
                                            }}
                                    }

                                }
                                Log.d("PreguntaSIZE",pregunta.size.toString())
                            }
                            if(cont< gameViewModel.getTodos().size ){

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

    fun popup(context:View){
        var popup = LayoutInflater.from(context.context).inflate(R.layout.popup_practice, null)
        var popupview = PopupWindow(popup, 500, 700, true)
        popupview.showAtLocation(context, Gravity.CENTER,0,0)
        popup.apply {
            si.setOnClickListener {
                popupview.dismiss()
                listener?.onNextQuestion("si", 0)

            }
            no.setOnClickListener {
                popupview.dismiss()
                listener?.onNextQuestion("no", 0)

            }
        }
    }

    fun check(int: Int):Boolean{
        if (gameViewModel.getPregunta().get(int) == gameViewModel.getRespuesta()){
            return true
            Log.d("PREGUNTAs",pregunta.size.toString())
            Log.d("RESPUESTA","CORRECTA")
        }else{
            return false
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
        fun newInstance(num : Int): Practica1Fragment{
            var frag = Practica1Fragment()
            frag.cont = num
            return frag
        }
    }
}
