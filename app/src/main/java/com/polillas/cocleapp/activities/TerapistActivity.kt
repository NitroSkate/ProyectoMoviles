package com.polillas.cocleapp.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Paciente
import com.polillas.cocleapp.Room.Entities.Pacientes
import com.polillas.cocleapp.Room.Entities.Puntaje
import kotlinx.android.synthetic.main.activity_terapist.*
import kotlinx.android.synthetic.main.addpatient.view.*
import kotlinx.android.synthetic.main.login.view.*
import java.util.*

class TerapistActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var db : FirebaseFirestore

    private lateinit var inflater : LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapist)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        inflater = LayoutInflater.from(this)

        var popup = inflater.inflate(R.layout.addpatient, null)
        var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        terapista_log.text = "Bienvenido/a " + auth.currentUser?.displayName



        add.setOnClickListener {
            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                add_patient.setOnClickListener {
                    popupview.dismiss()
                    if(TextUtils.isEmpty(name_patient.text) || TextUtils.isEmpty(lname_patient.text) || TextUtils.isEmpty(birth_patient.text)){
                        Toast.makeText(it.context, "No se ha podido iniciar sesion", Toast.LENGTH_SHORT).show()
                    }else {
                        val id = UUID.randomUUID().toString()
                        val list = mutableListOf<Puntaje>()
                        list.add(Puntaje("0", "0"))
                        val paciente = Pacientes(id,name_patient.text.toString(),lname_patient.text.toString(),birth_patient.text.toString(),lvl_patient.value,list)

                        db.collection("Pacient " + auth.currentUser?.email).document(id)
                            .set(paciente)
                            .addOnSuccessListener { Log.d("success", "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w("fail", "Error writing document", e) }
                    }
                }
            }
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@TerapistActivity, AccountActivity::class.java))
        }
    }
}
