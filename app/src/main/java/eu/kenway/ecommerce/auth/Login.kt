package eu.kenway.ecommerce.auth

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.kenway.ecommerce.products.Products
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentLoginBinding


class Login : Fragment() {

    private lateinit var bindinglogin: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mProgressDialog: Dialog

    var isRemembered=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPreferences= requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        bindinglogin = FragmentLoginBinding.inflate(inflater, container, false)

        isRemembered=sharedPreferences.getBoolean("CHECKBOX",false)

        if(isRemembered)
        {
            startActivity(Intent(requireContext(),Products::class.java))
            activity?.finish()


        }

        mProgressDialog = Dialog(requireContext())
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        bindinglogin.textView.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }



        bindinglogin.button.setOnClickListener {
            val email = bindinglogin.emailEt.text.toString()
            val password = bindinglogin.passET.text.toString()
            val checked:Boolean=bindinglogin.checkbox.isChecked
            mProgressDialog.show()



            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            editor.putString("EMAIL",email)
            editor.putBoolean("CHECKBOX",checked)

            editor.apply()

            checkCredentials(email, password)

        }

        return bindinglogin.root

    }


    private fun checkCredentials(email: String, password: String) {

        // check pass
        if (email.isBlank() || password.isBlank()) {
            mProgressDialog.hide()
            Snackbar.make(bindinglogin.root, "email and password should not be empty", Snackbar.LENGTH_SHORT).
            setBackgroundTint(Color.RED).show()
            return
        }


        if (email.isNotEmpty() && password.isNotEmpty()) {


            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {

                    mProgressDialog.hide()

                    val firebaseUser: FirebaseUser = it.result!!.user!!

                    startActivity(Intent(requireContext(), Products::class.java))

                } else {
                    mProgressDialog.hide()

                    Snackbar.make(bindinglogin.root, it.exception.toString(), Snackbar.LENGTH_SHORT).
                    setBackgroundTint(Color.RED).show()
                }
            }

        } else {
            mProgressDialog.hide()
            Toast.makeText(requireContext(), "Password not matching", Toast.LENGTH_LONG).show()
        }


    }


}