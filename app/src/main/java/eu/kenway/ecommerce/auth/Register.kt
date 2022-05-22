package eu.kenway.ecommerce.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
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



        binding.button.setOnClickListener{
            val email=binding.emailEt.text.toString()
            val password=binding.passET.text.toString()
            val confirmpass=binding.confirmPassEt.text.toString()

            if(email.isNotEmpty()&&password.isNotEmpty()&&confirmpass.isNotEmpty())
            {
                if(password==confirmpass)
                {
                   firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                       if(it.isSuccessful)
                       {
                           findNavController().navigate(R.id.action_register_to_login)
                       }
                       else{
                           Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_LONG).show()
                       }
                   }
                }
                else{
                     Toast.makeText(requireContext(),"Password not matching",Toast.LENGTH_LONG).show()
                }

            }
            else{
                Toast.makeText(requireContext(),"Password not matching",Toast.LENGTH_LONG).show()
            }


        }
        return binding.root
    }

}