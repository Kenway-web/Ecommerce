package eu.kenway.ecommerce.auth

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
import eu.kenway.ecommerce.databinding.FragmentRegisterBinding


class Register : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        firebaseAuth= FirebaseAuth.getInstance()

        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }


        binding.button.setOnClickListener{
            val firstname=binding.firstnamet.toString()
            val lastname=binding.lastname.toString()
            val email=binding.emailEt.text.toString()
            val password=binding.password.text.toString()
            val confirmpass=binding.confirmpass.text.toString()

            if(email.isNotEmpty()&&password.isNotEmpty()&&confirmpass.isNotEmpty()&&firstname.isNotEmpty()&&lastname.isNotEmpty())
            {
                if(password==confirmpass)
                {
                   firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                       if(it.isSuccessful)
                       {
                           val firebaseUser:FirebaseUser=it.result!!.user!!
                           Snackbar.make(binding.root, "You have registered successfully.Your user id is ${firebaseUser.uid}", Snackbar.LENGTH_SHORT).show()

                           findNavController().navigate(R.id.action_register_to_login)
                       }
                       else{
                           Snackbar.make(binding.root, it.exception!!.message.toString(), Snackbar.LENGTH_SHORT).show()
                       }
                   }
                }
                else{
                    // Toast.makeText(requireContext(),"Password not matching",Toast.LENGTH_LONG).show()
                    Snackbar.make(binding.root, "Password not matching", Snackbar.LENGTH_SHORT).show()
                }

            }
            else{
                Snackbar.make(binding.root, "Enter credentials", Snackbar.LENGTH_SHORT).show()
            }


        }
        return binding.root
    }

}