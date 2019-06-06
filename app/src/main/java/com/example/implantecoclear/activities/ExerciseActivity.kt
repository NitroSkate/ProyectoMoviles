package com.example.implantecoclear.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.implantecoclear.R
import com.example.implantecoclear.fragmentos_preguntas.Preguntas1Fragmento
import com.example.implantecoclear.fragmentos_preguntas.Preguntas2Fragmento

class ExerciseActivity : AppCompatActivity(), Preguntas1Fragmento.OnFragmentInteractionListener, Preguntas2Fragmento.OnFragmentInteractionListener {

    //private lateinit var p1 : Preguntas1Fragmento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        initfragment(0)
    }

    fun initfragment(id: Int){
        when(id){
            1 -> {
                var frag = Preguntas2Fragmento.newInstance()
                changefragment(R.id.ex_content, frag)
            }
            else -> {
                var frag = Preguntas1Fragmento.newInstance()
                changefragment(R.id.ex_content, frag)
            }
        }
    }

    override fun onNextQuestion(string: String, id: Int) {
        if(string == "next") {
            initfragment(id)
        }
        else{
            Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show()
        }
    }


    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}
