package eu.kenway.ecommerce.products. items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentHomeBinding
import eu.kenway.ecommerce.databinding.FragmentLoginBinding


class Home : Fragment() {

    private lateinit var bindingHome:FragmentHomeBinding

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


        bindingHome.imageSlider.setImageList(imageList)

        return bindingHome.root
    }


}