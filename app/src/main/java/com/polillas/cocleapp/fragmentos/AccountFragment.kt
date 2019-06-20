package com.polillas.cocleapp.fragmentos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

import com.polillas.cocleapp.R
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.send_bt
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
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        view.login.setOnClickListener {
            var popup = inflater.inflate(R.layout.login, null).apply {
                send_bt.text = "Iniciar Sesion"
            }
            var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                send_bt.setOnClickListener {
                    popupview.dismiss()
                    Toast.makeText(it.context, "Cuenta creada", Toast.LENGTH_SHORT).show()
                }
            }
        }

        view.register.setOnClickListener {
            var popup = inflater.inflate(R.layout.login, null).apply {
                send_bt.text = "Register"
            }
            var popupview = PopupWindow(popup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true)

            popupview.showAtLocation(it, Gravity.CENTER,0,0)
            popup.apply {
                send_bt.setOnClickListener {
                    popupview.dismiss()
                    listener?.onRegister(email.text.toString(), password.text.toString())
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
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onRegister(email: String, password: String)
    }

    companion object {

        @JvmStatic
        fun newInstance() : AccountFragment{
            var fragment = AccountFragment()
            return fragment
        }
    }
}
