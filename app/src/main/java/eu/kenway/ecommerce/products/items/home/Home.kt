package eu.kenway.ecommerce.products.items.home

import android.app.Dialog
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
import com.denzcoskun.imageslider.models.SlideModel
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.carttable.CartAdapater

import eu.kenway.ecommerce.databinding.FragmentHomeBinding
import eu.kenway.items.api.RetrofitHelper


class Home : Fragment() {

    private lateinit var bindingHome:FragmentHomeBinding
    private lateinit var adapter : ProductsAdapter
    lateinit var mainViewModel: MainViewModel
    private lateinit var mProgressDialog: Dialog




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingHome = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
        mProgressDialog = Dialog(requireContext())
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()


        val recycle=bindingHome.recyclerView
        recycle.layoutManager=LinearLayoutManager(requireContext())

        val itemService= RetrofitHelper.getInstance().create(ItemService::class.java)
        val repository= ItemRepository(itemService)
        mainViewModel= ViewModelProvider(this, Mainviewmodelfactory(repository)).get(MainViewModel::class.java)


        val imageList = ArrayList<SlideModel>() //
        imageList.add(SlideModel(R.drawable.banner_01, getString(R.string.catch1)))
        imageList.add(SlideModel(R.drawable.banner_02, getString(R.string.catch2)))
        imageList.add(SlideModel(R.drawable.banner_03, getString(R.string.catch3)))


     bindingHome.imageSlider.setImageList(imageList)


        bindingHome.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        val adapter= ProductsAdapter(requireContext())
        bindingHome.recyclerView.adapter=adapter

        mainViewModel.items.observe(viewLifecycleOwner, Observer {list->


            list?.let{
                mProgressDialog.hide()
                val product = it!!.data
                adapter.updatelist(product)
            }


        })

        return bindingHome.root
    }


}