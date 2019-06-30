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
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Viewmodel.PreguntaViewmodel
import kotlinx.android.synthetic.main.activity_score.*
import kotlinx.android.synthetic.main.popup_practice.view.*

class ScoreActivity : AppCompatActivity() {
    private lateinit var preguntaViewmodel: PreguntaViewmodel
    var puntaje: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preguntaViewmodel = ViewModelProviders.of(this).get(PreguntaViewmodel::class.java)
        setContentView(R.layout.activity_score)
        var d = intent.extras
        puntaje = d.getInt("AMD")
        if(puntaje != 100) {
            preguntaViewmodel.getAllPreguntas().observe(this, Observer {
                puntajeMax_tv.text = "/"+ it.size.toString()
            })
            puntaje_tv.text = puntaje.toString()
        } else {
            puntaje_textview.text = "Practica"
            puntaje_tv.text = "Finalizada"
            puntajeMax_tv.text = ""
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
