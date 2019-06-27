package com.polillas.cocleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    var puntaje: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        var d = intent.extras
        puntaje = d.getInt("AMD")
        if(puntaje != 100) {
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
