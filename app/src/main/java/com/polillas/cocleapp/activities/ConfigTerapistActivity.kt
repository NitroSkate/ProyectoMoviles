package com.polillas.cocleapp.activities

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
                        || !TextUtils.isEmpty(apellido_et_edit.text) || !TextUtils.isEmpty(et_password_edit.text) || !TextUtils.isEmpty(conf_password_edit.text)){

                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nombre_et_edit.text.toString()+ " " + apellido_et_edit.text.toString())
                            .build()


                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if(task.isSuccessful) {
                                    Log.d("Perfil", "Nombre actualizado")
                                }
                            }

                        user?.updatePassword(et_password_edit.text.toString())
                            ?.addOnCompleteListener { task ->
                                if(task.isSuccessful) {
                                    Log.d("Perfil", "Contraseña actualizado")
                                }
                            }

                        popupview.dismiss()
                    }
                }
            }
        }

    }
}
