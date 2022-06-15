package eu.kenway.ecommerce.products.items

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.carttable.CartAdapater
import eu.kenway.ecommerce.carttable.CartEntity
import eu.kenway.ecommerce.carttable.CartViewModel
import eu.kenway.ecommerce.carttable.Icartvadapter
import eu.kenway.ecommerce.databinding.FragmentCartBinding
import eu.kenway.ecommerce.databinding.FragmentHomeBinding


class Cart : Fragment() ,Icartvadapter{

    lateinit var bindingCart:FragmentCartBinding
    lateinit var cartViewModel: CartViewModel

    lateinit var preferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingCart = FragmentCartBinding.inflate(inflater, container, false)



// code changed here
        preferences=requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val email=preferences.getString("EMAIL","")

        bindingCart.recyclerViewCart.layoutManager=LinearLayoutManager(requireContext())
        val adapter=CartAdapater(requireContext(),this)
        bindingCart.recyclerViewCart.adapter=adapter

        cartViewModel=ViewModelProvider(this).get(CartViewModel::class.java)
        var total=0
        cartViewModel.userDataUsername(email.toString()).observe(viewLifecycleOwner,Observer{list->

            val x=0
            list?.let{
                adapter.updatelist(it)
                var str: String
                for (x in list) {
                    val tempStr: String = x.price
                    str = tempStr.substring(0, tempStr.length - 2)
                    total += str.toInt()
                }
            }

            bindingCart.totalprice.text = "Cart Price : ${total}.00"
            total=0
            (activity as AppCompatActivity).supportActionBar?.title = "Cart (${list.size} +items)"

        })

        return bindingCart.root
    }

    override fun onItemClicked(entity: CartEntity) {
            cartViewModel.deleteItem(entity)

    }


}