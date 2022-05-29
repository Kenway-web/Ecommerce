package eu.kenway.ecommerce.products.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.kenway.ecommerce.databinding.FragmentHelpBinding

// TODO: Rename parameter arguments, choose names that match

class Help : Fragment() {

    lateinit var binding:FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHelpBinding.inflate(inflater,container,false)

      return binding.root
    }


}