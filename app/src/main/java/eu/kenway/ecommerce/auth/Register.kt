package eu.kenway.ecommerce.auth

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.R.*
import eu.kenway.ecommerce.databinding.FragmentRegisterBinding
import eu.kenway.ecommerce.firestore.Firetoreclass
import eu.kenway.ecommerce.firestore.models.User


class Register : Fragment() {


    lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mProgressDialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        mProgressDialog = Dialog(requireContext())
        mProgressDialog.setContentView(R.layout.dialog_progress)
         mProgressDialog.setCancelable(false)
         mProgressDialog.setCanceledOnTouchOutside(false)


        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar


        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(drawable.ic_baseline_arrow_back_ios_24)
        }




        binding.button.setOnClickListener {




            val firstname = binding.firstnamet.toString()
            val lastname = binding.lastname.toString()
            val email = binding.emailEt.text.toString()
            val password = binding.password.text.toString()
            val confirmpass = binding.confirmpass.text.toString()

            // registerUser()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmpass.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty()) {


                if (password == confirmpass) {

                    mProgressDialog.show()
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                              mProgressDialog.hide()
                                val firebaseUser: FirebaseUser = it.result!!.user!!


                                val user=User(
                                    firebaseUser.uid,
                                    firstname.trim(),
                                    lastname.trim(),
                                    email.trim(),

                                )


                              Firetoreclass().registerUser(this,user)

                                // Snackbar.make(binding.root, "You have registered successfully.Your user id is ${firebaseUser.uid}", Snackbar.LENGTH_SHORT).
                               //  setBackgroundTint(Color.GREEN).show()

                                findNavController().navigate(R.id.action_register_to_login)


                            } else {
                                mProgressDialog.hide()
                                Snackbar.make(
                                    binding.root,
                                    it.exception!!.message.toString(),
                                    Snackbar.LENGTH_SHORT
                                ).setBackgroundTint(Color.RED).show()
                            }
                        }
                } else {
                    mProgressDialog.hide()
                    // Toast.makeText(requireContext(),"Password not matching",Toast.LENGTH_LONG).show()
                    Snackbar.make(binding.root, "Password not matching", Snackbar.LENGTH_SHORT).
                    setBackgroundTint(Color.RED).show()
                }

            } else {
                mProgressDialog.hide()
                Snackbar.make(binding.root, "Enter credentials", Snackbar.LENGTH_SHORT).show()
            }


        }


        return binding.root
    }


    fun userRegisterSuccess()
    {
        mProgressDialog.hide()

        Toast.makeText(
           requireActivity(),
            resources.getString(R.string.register_success),
            Toast.LENGTH_SHORT
        ).show()

    }
}

