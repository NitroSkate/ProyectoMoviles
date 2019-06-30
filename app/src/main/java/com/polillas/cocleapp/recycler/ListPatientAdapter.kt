package com.polillas.cocleapp.recycler

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Pacientes
import com.polillas.cocleapp.Room.Entities.Puntaje
import kotlinx.android.synthetic.main.addpatient.view.*
import kotlinx.android.synthetic.main.listpat_elements.view.*
import kotlinx.android.synthetic.main.popup_info_pat.view.*
import kotlinx.android.synthetic.main.popup_info_pat.view.lname_patient

class ListPatientAdapter (var patients : List<Pacientes>,val click: (Pacientes) -> Unit, var auth : FirebaseAuth, var db : FirebaseFirestore) : RecyclerView.Adapter<ListPatientAdapter.ViewHolder>(){

    //private var patients = emptyList<Pacientes>()

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pacientes: Pacientes, click: (Pacientes) -> Unit, auth: FirebaseAuth, db: FirebaseFirestore) = with(itemView){
            Log.d("paciente", pacientes.nombre)
            nombre_patient_info.text = pacientes.nombre
            apellido_patient_info.text = pacientes.apellido
            birth_patient_info.text = pacientes.fechaIngreso
            info_pat_bt.setOnClickListener {
                popup(1, it, pacientes, auth, db)
                click(pacientes)
            }
            edit_pat_bt.setOnClickListener {
                popup(2,it,pacientes,auth,db)
                click(pacientes)
            }
            del_pat_bt.setOnClickListener {
                db.collection("Pacient " + auth.currentUser?.email).document(pacientes.id.toString())
                    .delete()
                click(pacientes)
            }

        }
        private fun popup(int: Int,context: View, item: Pacientes, auth: FirebaseAuth, db: FirebaseFirestore){
            when(int) {

                1 -> {
                    var popup = LayoutInflater.from(context.context).inflate(R.layout.popup_info_pat, null)
                    var popupview = PopupWindow(
                        popup,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        true
                    )

                    popupview.showAtLocation(context, Gravity.CENTER, 0, 0)
                    popup.apply {
                        id_patient.text = "ID paciente: " + item.id
                        fname_patient.text = "Nombre del paciente: " + item.nombre
                        lname_patient.text = "Apellido del paciente: " + item.apellido
                        birth_patient_inf.text = "Fecha de ingreso del paciente: " + item.fechaIngreso
                        patient_level.text = "Nivel del paciente: " + item.nivel
                        this.setOnClickListener {
                            popupview.dismiss()
                        }
                    }
                }
                2 ->{
                    var popup = LayoutInflater.from(context.context).inflate(R.layout.addpatient, null)
                    var popupview = PopupWindow(
                        popup,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        true
                    )

                    popupview.showAtLocation(context, Gravity.CENTER, 0, 0)
                    popup.apply {
                        editthis.text = "Edite la informacion del paciente"
                        add_patient.text = "Confirmar cambios"
                        add_patient.setOnClickListener {
                            val id = item.id.toString()
                            val list = mutableListOf<Puntaje>()
                            list.add(Puntaje("0", "0"))
                            val paciente = Pacientes(id,name_patient.text.toString(),lname_patient.text.toString(),birth_patient.dayOfMonth.toString()
                                    + "/" + birth_patient.month.toString() + "/" + birth_patient.year.toString(),lvl_patient.value.toString(),list)

                            db.collection("Pacient " + auth.currentUser?.email).document(id)
                                .set(paciente)
                                .addOnSuccessListener { Log.d("success", "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w("fail", "Error writing document", e) }
                            popupview.dismiss()
                        }

                    }
                }
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPatientAdapter.ViewHolder {
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listpat_elements, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = patients.size

    override fun onBindViewHolder(holder: ListPatientAdapter.ViewHolder, position: Int) = holder.bind(patients[position], click, auth, db)

}