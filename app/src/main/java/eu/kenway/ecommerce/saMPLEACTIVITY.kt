package eu.kenway.ecommerce

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import eu.kenway.ecommerce.carttable.CartEntity
import eu.kenway.ecommerce.carttable.CartViewModel
import eu.kenway.ecommerce.databinding.ActivitySaMpleactivityBinding
import eu.kenway.ecommerce.products.Products



class saMPLEACTIVITY : AppCompatActivity() {
    private  lateinit var bindingsample:ActivitySaMpleactivityBinding
    lateinit var cartviewmodel: CartViewModel
    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingsample = ActivitySaMpleactivityBinding.inflate(layoutInflater)
        setContentView(bindingsample.root)
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)

        }

        val bundle = intent.extras
        val title = bundle?.getString("title")
        val price = bundle?.getString("price")
        val quantity = bundle?.getString("quantity")
        val expiryDate = bundle?.getString("expiryDate")

        bindingsample.Itemname.text="Title : " + title.toString()
        bindingsample.price.text="Price : $" + price.toString()
        bindingsample.Expiry.text="Quantity : " + quantity.toString()
        bindingsample.quantity.text="ExpiryDate : $" + expiryDate.toString()


        when(title.toString())
        {
            "Bread"-> bindingsample.imageView2.setImageResource(R.drawable.product_01)
            "Sugar"-> bindingsample.imageView2.setImageResource(R.drawable.sugar)
            "Sunflower Oil"-> bindingsample.imageView2.setImageResource(R.drawable.sunflower)
            "Rice"->bindingsample.imageView2.setImageResource(R.drawable.product_03)
            "Salt"->bindingsample.imageView2.setImageResource(R.drawable.product_04)
            else ->bindingsample.imageView2.setImageResource(R.drawable.product_02)
        }


        // code changed here

        preferences=getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val email=preferences.getString("EMAIL","")
        //val username=this.intent.getStringExtra("username")
        Log.d("hello","$email")
        cartviewmodel= ViewModelProvider(this).get(CartViewModel::class.java)
        bindingsample.addcard.setOnClickListener {
            Log.d("hello","hai")
            Toast.makeText(this,"Added to cart",Toast.LENGTH_SHORT).show()
            cartviewmodel.addItem(
                CartEntity(
                    0,
                    email.toString(),
                    title.toString(),
                    price.toString(),
                    quantity.toString()
                )
            )
            val intent=Intent(this, Products::class.java)
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      when(item.getItemId())
      {
          android.R.id.home->{
              onBackPressed()
              return true
          }
      }
       return super.onOptionsItemSelected(item)
    }

}