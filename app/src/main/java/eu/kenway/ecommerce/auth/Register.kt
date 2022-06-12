package eu.kenway.ecommerce.auth

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.R.*
import eu.kenway.ecommerce.databinding.FragmentRegisterBinding


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


            val firstname = binding.firstnamet.text.toString()
            val lastname = binding.lastname.toString()
            val email = binding.emailEt.text.toString()
            val password = binding.password.text.toString()
            val confirmpass = binding.confirmpass.text.toString()

            // registerUser()

            if(!email.isValidEmail()||email.isEmpty())
            {
                mProgressDialog.hide()
                Snackbar.make(binding.root, "Invalid email", Snackbar.LENGTH_SHORT).
                setBackgroundTint(Color.RED).show()

            }
            else if(firstname.isBlank()||lastname.isBlank())
            {
                mProgressDialog.hide()
                Snackbar.make(binding.root, "First name and last name should not be empty", Snackbar.LENGTH_SHORT).
                setBackgroundTint(Color.RED).show()
            }

            else if(password.length<6||password.isEmpty())
            {
                mProgressDialog.hide()
                Snackbar.make(binding.root, "Password shouldn't be empty and should be of 6 characters ", Snackbar.LENGTH_SHORT).
                setBackgroundTint(Color.RED).show()

            }

           else if(password!=confirmpass||password.isEmpty()||confirmpass.isEmpty())
            {
                mProgressDialog.hide()
                Snackbar.make(binding.root, "Invalid password or Password not matching ", Snackbar.LENGTH_SHORT).
                setBackgroundTint(Color.RED).show()
            }



           else  {

                    mProgressDialog.show()
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                              mProgressDialog.hide()
                                val firebaseUser: FirebaseUser = it.result!!.user!!


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
                }



        }


        return binding.root
    }

    fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

}

