package eu.kenway.ecommerce.auth

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.kenway.ecommerce.products.Products
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentLoginBinding
import eu.kenway.ecommerce.firestore.Firetoreclass
import eu.kenway.ecommerce.firestore.models.User
import kotlin.properties.Delegates


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
            mProgressDialog.show()



            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            editor.putString("EMAIL",email)

            editor.apply()

            checkCredentials(email, password)

        }

        return bindinglogin.root

    }


    private fun checkCredentials(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {


            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                if (it.isSuccessful) {

                    mProgressDialog.hide()

                    val firebaseUser: FirebaseUser = it.result!!.user!!
                    Firetoreclass().getUserDetails(this@Login)
                    startActivity(Intent(requireContext(), Products::class.java))

                } else {
                    mProgressDialog.hide()
                    Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }

        } else {
            mProgressDialog.hide()
            Toast.makeText(requireContext(), "Password not matching", Toast.LENGTH_LONG).show()
        }


    }

    // TODO Step 7: Create a function to notify user that logged in success and details are fetched from Cloud Firestore.
    // START
    /**
     * A function to notify user that logged in success and get the user details from the FireStore database after authentication.
     */
    fun userLoggedInSuccess(user: User) {


        // Print the user details in the log as of now.
        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Email: ", user.email)

        // Redirect the user to Main Screen after log in.
        startActivity(Intent(requireContext(), Products::class.java))
        // END

    }
}