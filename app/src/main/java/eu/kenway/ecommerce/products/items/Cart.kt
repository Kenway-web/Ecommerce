package eu.kenway.ecommerce.products.items

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.carttable.CartAdapater
import eu.kenway.ecommerce.carttable.CartViewModel
import eu.kenway.ecommerce.databinding.FragmentCartBinding
import eu.kenway.ecommerce.databinding.FragmentHomeBinding


class Cart : Fragment() {

    lateinit var bindingCart:FragmentCartBinding
    lateinit var cartViewModel: CartViewModel
    lateinit var cartAdapater: CartAdapater
    lateinit var preferences: SharedPreferences

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingCart = FragmentCartBinding.inflate(inflater, container, false)



// code changed here
        preferences=requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val email=preferences.getString("EMAIL","")

        cartViewModel=ViewModelProvider(this).get(CartViewModel::class.java)
        var total=0
        cartViewModel.userDataUsername(email.toString()).observe(this,Observer{data->

            if(data.isEmpty())
            {
                (activity as AppCompatActivity).supportActionBar?.title = "cart (${data.size} items)"
                Snackbar.make(bindingCart.root, "Empty cart", Snackbar.LENGTH_SHORT).
                setBackgroundTint(Color.RED).show()
            }

            else{
                var str: String
                for (x in data) {
                    val tempStr: String = x.price
                    str = tempStr.substring(0, tempStr.length - 2)
                    total += str.toInt()
                    Log.d("cartPrice", "${x.price}")
                    (activity as AppCompatActivity).supportActionBar?.title = "cart (${data.size} items)"
                    bindingCart.totalprice.text="Total Amount:  ${total}.00"
                    cartAdapater= CartAdapater(data,cartViewModel)
                    Log.d("hello",data[0].price)
                    bindingCart.recyclerViewCart.adapter=cartAdapater
                }
            }

            //val totalPrice : TextView = view.findViewById(R.id.totalprice)
            //totalPrice.text = "Cart Price : ${total}.00"
            //total=0

        })
        return bindingCart.root
    }

}