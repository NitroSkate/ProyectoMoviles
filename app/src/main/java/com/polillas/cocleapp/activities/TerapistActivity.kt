package com.polillas.cocleapp.activities

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.polillas.cocleapp.MainActivity
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Pacientes
import com.polillas.cocleapp.Room.Entities.Puntaje
import com.polillas.cocleapp.recycler.ListPatientAdapter
import kotlinx.android.synthetic.main.activity_terapist.*
import kotlinx.android.synthetic.main.addpatient.view.*
import java.util.*
import kotlin.collections.ArrayList

/* TODO Archivo de kotlin encargado de crear la vista donde el terapeuta controlara la informacion del paciente
TODO El codigo funciona en base a firebase por medio del uso de firestore y sus metodos de recopilacion, agregacion
TODO , edicion y eliminacion de datos en la base de datos en la nube. Hace uso de la autentificacion para mantenerse en
TODO esta ventana. Los pacientes se muestran por medio de una vista recicladora.
 */

class TerapistActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var db : FirebaseFirestore

    private lateinit var inflater : LayoutInflater

    private lateinit var listPatientAdapter: ListPatientAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terapist)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        inflater = LayoutInflater.from(this)

        var popup = inflater.inflate(R.layout.addpatient, null)
        var popupview =
            PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

        if(auth.currentUser?.displayName == null || auth.currentUser?.displayName == "" || auth
                .currentUser?.displayName == " "){
            terapista_log.text = "Bienvenido/a "+ auth.currentUser?.email
        } else{
            terapista_log.text = "Bienvenido/a " + auth.currentUser?.displayName
        }


        retrievedocuments()


        add.setOnClickListener {
            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                add_patient.setOnClickListener {
                    popupview.dismiss()
                    if(TextUtils.isEmpty(name_patient.text) || TextUtils.isEmpty(lname_patient.text)){
                        Toast.makeText(it.context, "No se ha podido ingresar al paciente", Toast.LENGTH_SHORT).show()
                    }else {
                        val id = UUID.randomUUID().toString()
                        val list = mutableListOf<Puntaje>()
                        list.add(Puntaje("0", "0"))
                        val paciente = Pacientes(id,name_patient.text.toString(),lname_patient.text.toString(),birth_patient.dayOfMonth.toString()
                            + "/" + birth_patient.month.toString() + "/" + birth_patient.year.toString(),nivel_patient.text.toString(),list)

                        db.collection("Pacient " + auth.currentUser?.email).document(id)
                            .set(paciente)
                            .addOnSuccessListener { Log.d("success", "DocumentSnapshot successfully written!") }
                            .addOnFailureListener { e -> Log.w("fail", "Error writing document", e) }
                    }
                    retrievedocuments()
                }
            }

        }

        tp_conf1.setOnClickListener {
            startActivity(Intent(this@TerapistActivity, ConfigTerapistActivity::class.java))
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@TerapistActivity, AccountActivity::class.java))
        }
    }

    fun retrievedocuments(){
        val lista = ArrayList<String?>()
        val listp = mutableListOf<Puntaje>()
        val list = mutableListOf<Pacientes>()
        listp.add(Puntaje("0", "0"))
        db.collection("Pacient " + auth.currentUser?.email)
            .get()
            .addOnSuccessListener { results ->
                for (document in results) {
                    Log.d("abr", document.getString("nombre"))
                    val paciente = Pacientes(document.id, document.getString("nombre"), document.getString("apellido"), document.getString("fechaIngreso"), document.getString("nivel"), listp)
                    list.add(paciente)
                }
                initrecycler(list)
            }
            .addOnFailureListener { exc ->
                Log.d("fail", "Murio", exc)
            }


    }

    fun initrecycler(lista : List<Pacientes>){
        val linearlayoutmanager = LinearLayoutManager(this)
        listPatientAdapter = ListPatientAdapter(lista, {item: Pacientes -> onClickPatient(item)}, auth, db)

        recycler.adapter = listPatientAdapter
        listPatientAdapter.notifyDataSetChanged()
        recycler.apply{
            setHasFixedSize(true)
            layoutManager = linearlayoutmanager
        }
    }

    private fun onClickPatient(item: Pacientes){
        Log.d("objeto", item.nombre)
        retrievedocuments()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@TerapistActivity, MainActivity::class.java))
    }
}
