package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.polillas.cocleapp.MainActivity

import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import com.google.android.material.snackbar.Snackbar
import com.polillas.cocleapp.R
import com.polillas.cocleapp.constants.AppConstants
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    private lateinit var preguntaViewmodel: PreguntaViewmodel
    var puntaje: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        setContentView(R.layout.activity_score)
        var d = intent.extras
        puntaje = d.getInt("AMD")
        if(puntaje >= AppConstants.MAX_PREGUNTAS){
            Snackbar.make(score_ll,"Puntaje Perfecto!",Snackbar.LENGTH_LONG).show()
        }else{
            Snackbar.make(score_ll,"Muy Bien!",Snackbar.LENGTH_LONG).show()
        }

        if(puntaje != 100) {
            /*preguntaViewmodel.getAllPreguntas().observe(this, Observer {
                puntajeMax_tv.text = "/"+ it.size.toString()
            })*/
            puntaje_tv.text = puntaje.toString()
            //puntajeMax_tv.text = "/" + AppConstants.MAX_PREGUNTAS

        } else {
            puntaje_textview.text = "Practica"
            puntaje_tv.text = "Finalizada"
           puntajeMax_tv.text = puntaje.toString()
        }
        this.apply {
            lL_scoreaceptar.setOnClickListener {
                val intent = Intent(this@ScoreActivity, com.polillas.cocleapp.MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(puntaje != 100) {
            startActivity(Intent(this@ScoreActivity, ExerciseActivity::class.java))
        } else {
            startActivity(Intent(this@ScoreActivity, PracticeActivity::class.java))
        }
    }
}
