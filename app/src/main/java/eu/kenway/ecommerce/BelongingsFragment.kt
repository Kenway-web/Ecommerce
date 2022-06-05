package eu.kenway.ecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import eu.kenway.ecommerce.databinding.ActivityMainBinding.inflate
import eu.kenway.ecommerce.databinding.FragmentBelongingsBinding
import eu.kenway.ecommerce.databinding.FragmentHomeBinding

class BelongingsFragment : Fragment() {

    lateinit var bindingBelongingsFragment:FragmentBelongingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingBelongingsFragment = FragmentBelongingsBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Product Details"

        return inflater.inflate(R.layout.fragment_belongings, container, false)

        return bindingBelongingsFragment.root
    }


}