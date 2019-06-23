package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.polillas.cocleapp.R
import com.polillas.cocleapp.fragmentos_preguntas.Preguntas1Fragmento
import com.polillas.cocleapp.fragmentos_preguntas.Preguntas2Fragmento

class ExerciseActivity : AppCompatActivity(), Preguntas1Fragmento.OnFragmentInteractionListener, Preguntas2Fragmento.OnFragmentInteractionListener {

    //private lateinit var p1 : Preguntas1Fragmento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        initfragment(1)
    }

    fun initfragment(id: Int){
        /*when(id){
            1 -> {
                var frag = Preguntas2Fragmento.newInstance()
                changefragment(R.id.ex_content, frag)
            }*/
        var frag = Preguntas1Fragmento.newInstance(id)
        changefragment(R.id.ex_content, frag)
    }

    override fun onNextQuestion(string: String, id: Int) {
        if(string == "next") {
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            initfragment(id)
        }
        else{
            val intent = Intent(this@ExerciseActivity, ScoreActivity::class.java)
            startActivity(intent)
        }
    }


    fun changefragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id,frag).commit()
    }
}
