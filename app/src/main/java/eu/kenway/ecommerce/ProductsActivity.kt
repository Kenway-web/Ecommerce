package eu.kenway.ecommerce

import android.R
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import eu.kenway.ecommerce.databinding.ActivityProductsBinding


class ProductsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title ="Products"



    }
}