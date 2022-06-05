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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import eu.kenway.ecommerce.R

import eu.kenway.ecommerce.databinding.FragmentHomeBinding
import eu.kenway.items.Repository.ItemRepository
import eu.kenway.items.api.ItemService
import eu.kenway.items.api.ProductsAdapter
import eu.kenway.items.api.RetrofitHelper
import eu.kenway.items.viewmodel.MainViewModel
import eu.kenway.items.viewmodel.Mainviewmodelfactory


class Home : Fragment() {

    private lateinit var bindingHome:FragmentHomeBinding
    private lateinit var adapter : ProductsAdapter
    lateinit var mainViewModel: MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingHome = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"


        val recycle=bindingHome.recyclerView
        recycle.layoutManager=LinearLayoutManager(requireContext())

        val quoteService= RetrofitHelper.getInstance().create(ItemService::class.java)
        val repository= ItemRepository(quoteService)
        mainViewModel= ViewModelProvider(this, Mainviewmodelfactory(repository)).get(MainViewModel::class.java)


        val imageList = ArrayList<SlideModel>() //
        imageList.add(SlideModel(R.drawable.banner_01, "The animal population decreased by 58 percent in 42 years."))
        imageList.add(SlideModel(R.drawable.banner_02, "Elephants and tigers may become extinct."))
        imageList.add(SlideModel(R.drawable.banner_03, "And people do that."))



     bindingHome.imageSlider.setImageList(imageList)


        mainViewModel.items.observe(viewLifecycleOwner, Observer {
            Log.d("Key1",it.data.toString())
            val product = it!!.data
            adapter = ProductsAdapter(product)
            recycle.adapter = adapter

        })

        return bindingHome.root
    }


}