package eu.kenway.ecommerce.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.X
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.kenway.ecommerce.ProductsActivity
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentLoginBinding


class Login : Fragment() {

    private lateinit var bindinglogin: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        bindinglogin= FragmentLoginBinding.inflate(inflater,container,false)


        firebaseAuth=FirebaseAuth.getInstance()

        bindinglogin.textView.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }


        bindinglogin.button.setOnClickListener {

                val email=bindinglogin.emailEt.text.toString()
                val password=bindinglogin.passET.text.toString()

                checkCredentials(email,password)

            }

        return bindinglogin.root

    }

    private fun checkCredentials(email: String, password: String) {

        if(email.isNotEmpty()&&password.isNotEmpty())
        {

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful)
                {
                    val firebaseUser:FirebaseUser=it.result!!.user!!
                   startActivity(Intent(requireContext(),ProductsActivity::class.java))

                }
                else{
                    Toast.makeText(requireContext(),it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }
        else{
            Toast.makeText(requireContext(),"Password not matching", Toast.LENGTH_LONG).show()
        }


    }



}