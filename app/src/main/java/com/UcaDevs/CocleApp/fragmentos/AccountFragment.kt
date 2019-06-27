package com.UcaDevs.CocleApp.fragmentos

import android.content.Context
import android.os.Bundle
import android.text.TextUtils

import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
//import com.google.firebase.auth.FirebaseAuth

import com.UcaDevs.CocleApp.R
import kotlinx.android.synthetic.main.fragment_new_account.view.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login, container, false)
        view.lL_login.setOnClickListener {
            if(TextUtils.isEmpty(email.text) || TextUtils.isEmpty(password.text)){
                Toast.makeText(it.context, "No se ha podido iniciar sesion", Toast.LENGTH_SHORT).show()
            }else {
                listener?.onLogin(email.text.toString(), password.text.toString())
            }
        }
        
        view.register_tv.setOnClickListener {
            var popup = inflater.inflate(R.layout.fragment_new_account, null)
            var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)
    
            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                close_popup.setOnClickListener {
                    popupview.dismiss()
                }
                lL_new.setOnClickListener {
                    popupview.dismiss()
                    if(TextUtils.isEmpty(et_email.text) || TextUtils.isEmpty(et_password.text) || et_password.text.toString() != conf_password.text.toString()){
                        Toast.makeText(it.context, "No se ha podido crear la cuenta", Toast.LENGTH_SHORT).show()
                    }else {
                        listener?.onRegister(et_email.text.toString(), et_password.text.toString())
                    }
                }
            }
        }
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener") as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onRegister(email: String, password: String)
        fun onLogin(email: String, password: String)
    }

    companion object {

        @JvmStatic
        fun newInstance() : AccountFragment{
            var fragment = AccountFragment()
            return fragment
        }
    }
}
