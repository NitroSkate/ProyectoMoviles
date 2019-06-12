package com.example.implantecoclear.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.implantecoclear.MainActivity
import com.example.implantecoclear.R
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        this.apply {
            finish_bt.setOnClickListener {
                val intent = Intent(this@ScoreActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
