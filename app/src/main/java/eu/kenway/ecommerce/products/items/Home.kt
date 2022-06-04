package eu.kenway.ecommerce.products. items

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.api.Qservice
import eu.kenway.ecommerce.api.RetrofitHelper
import eu.kenway.ecommerce.databinding.FragmentHomeBinding

import eu.kenway.ecommerce.products.Belongings
import eu.kenway.ecommerce.products.ProductsAdapter

import retrofit2.create


class Home : Fragment() {

    private lateinit var bindingHome:FragmentHomeBinding
    private var nameList : MutableList<Belongings> = mutableListOf()
    private lateinit var sampleAdapter: ProductsAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingHome = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"



        val imageList = ArrayList<SlideModel>() //
        imageList.add(SlideModel(R.drawable.banner_01, "The animal population decreased by 58 percent in 42 years."))
        imageList.add(SlideModel(R.drawable.banner_02, "Elephants and tigers may become extinct."))
        imageList.add(SlideModel(R.drawable.banner_03, "And people do that."))





        return bindingHome.root
    }


}