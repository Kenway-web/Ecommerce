package eu.kenway.ecommerce.products.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import eu.kenway.ecommerce.R


class Cart : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.cart)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


}