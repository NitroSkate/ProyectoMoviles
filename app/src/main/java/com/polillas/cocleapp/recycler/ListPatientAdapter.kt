package com.polillas.cocleapp.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polillas.cocleapp.R
import com.polillas.cocleapp.Room.Entities.Pacientes
import kotlinx.android.synthetic.main.listpat_elements.view.*

class ListPatientAdapter (var patients : List<Pacientes>,val click: (Pacientes) -> Unit) : RecyclerView.Adapter<ListPatientAdapter.ViewHolder>(){

    //private var patients = emptyList<Pacientes>()

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pacientes: Pacientes, click: (Pacientes) -> Unit) = with(itemView){
            Log.d("paciente", pacientes.nombre)
            nombre_patient_info.text = pacientes.nombre
            apellido_patient_info.text = pacientes.apellido
            birth_patient_info.text = pacientes.fechaIngreso
            this.setOnClickListener {
                click(pacientes)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPatientAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listpat_elements, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = patients.size

    override fun onBindViewHolder(holder: ListPatientAdapter.ViewHolder, position: Int) = holder.bind(patients[position], click)

}