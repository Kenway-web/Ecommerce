package eu.kenway.ecommerce.auth

import android.content.Intent
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
import eu.kenway.ecommerce.Products
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentLoginBinding
import eu.kenway.ecommerce.firestore.Firetoreclass
import eu.kenway.ecommerce.firestore.models.User


class Login : Fragment() {

    private lateinit var bindinglogin: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        bindinglogin = FragmentLoginBinding.inflate(inflater, container, false)


        firebaseAuth = FirebaseAuth.getInstance()

        bindinglogin.textView.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }


        bindinglogin.button.setOnClickListener {

            val email = bindinglogin.emailEt.text.toString()
            val password = bindinglogin.passET.text.toString()

            checkCredentials(email, password)

        }

        return bindinglogin.root

    }


    private fun checkCredentials(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val firebaseUser: FirebaseUser = it.result!!.user!!
                    Firetoreclass().getUserDetails(this@Login)
                    startActivity(Intent(requireContext(),Products::class.java))

                } else {
                    Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }

        } else {
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