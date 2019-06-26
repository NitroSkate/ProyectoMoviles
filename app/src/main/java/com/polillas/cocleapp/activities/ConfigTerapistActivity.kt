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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.activity_config_terapist.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_new_account.view.*
import java.io.IOException
import java.lang.Exception

class ConfigTerapistActivity : AppCompatActivity() {

    private lateinit var user : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_terapist)
        var user = FirebaseAuth.getInstance().currentUser
        lL_Cuenta_act.setOnClickListener {
            var popup = LayoutInflater.from(this).inflate(R.layout.fragment_edit_profile, null)
            var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                lL_new_edit.setOnClickListener {
                    if(et_password_edit.text.toString() == conf_password_edit.text.toString() || !TextUtils.isEmpty(nombre_et_edit.text)
                        || !TextUtils.isEmpty(apellido_et_edit.text) || !TextUtils.isEmpty(et_password_edit.text) || !TextUtils.isEmpty(conf_password_edit.text)
                        || et_password_edit.text.toString() != " " || et_password_edit.text.toString() != "" || et_password_edit.text.toString() != null){

                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nombre_et_edit.text.toString()+ " " + apellido_et_edit.text.toString())
                            .build()

                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("Perfil", "Nombre actualizado")
                                }
                            }
                        try {
                            user?.updatePassword(et_password_edit.text.toString())
                                ?.addOnCompleteListener { task ->
                                    if(task.isSuccessful) {
                                        Log.d("Perfil", "Contraseña actualizado")
                                    } else{
                                        Log.d("F", "F in the chat")
                                    }
                                }
                        } catch(e : Exception){
                            Log.d("F", "F in da chat")
                            Toast.makeText(this.context, "No se pudo guardar la contraseña", Toast.LENGTH_SHORT).show()
                        } catch (e: Error){
                            Log.d("F", "Super F")
                        }
                        popupview.dismiss()
                    } else{
                        Toast.makeText(this.context, "Llene los campos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return_act.setOnClickListener {
            startActivity(Intent(this@ConfigTerapistActivity, AccountActivity::class.java))
        }

    }
}
