package eu.kenway.ecommerce.products. items

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
import eu.kenway.items.models.ItemList
import eu.kenway.items.viewmodel.MainViewModel
import eu.kenway.items.viewmodel.Mainviewmodelfactory


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


        mainViewModel.items.observe(viewLifecycleOwner, Observer {

            Log.d("Key1",it.data.toString())
            val product = it!!.data
            if(!product.isEmpty())
            {
                mProgressDialog.hide()
            }
            adapter = ProductsAdapter(requireContext(),product)
            recycle.adapter = adapter


        })

        return bindingHome.root
    }




}