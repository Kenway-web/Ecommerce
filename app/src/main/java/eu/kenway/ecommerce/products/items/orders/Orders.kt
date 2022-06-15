package eu.kenway.ecommerce.products.items.orders

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
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentOrdersBinding
import eu.kenway.ecommerce.products.items.home.ProductsAdapter
import eu.kenway.items.api.RetrofitHelper


class orders : Fragment() {


    private lateinit var bindingorders:FragmentOrdersBinding
    lateinit var viewmodel: oMainViewModel
    private lateinit var adapter: OrdersAdapter
    private lateinit var mProgressDialog: Dialog




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingorders = FragmentOrdersBinding.inflate(inflater, container, false)
        mProgressDialog = Dialog(requireContext())
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()


        (activity as AppCompatActivity).supportActionBar?.title = "Orders"


        val recycle=bindingorders.recyclerView
        recycle.layoutManager=LinearLayoutManager(requireContext())

        val orderservice=RetrofitHelper.getInstance().create(orderService::class.java)
        val repository=orderRepository(orderservice)

        bindingorders.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        val adapter= OrdersAdapter(requireContext())
        bindingorders.recyclerView.adapter=adapter
        viewmodel=ViewModelProvider(this,oMainviewmodelfactory(repository)).get(oMainViewModel::class.java)

        viewmodel.items.observe(viewLifecycleOwner, Observer { list->

            list?.let{
                mProgressDialog.hide()
                val product = it!!.data[0].arrData
                adapter.updatelist(product)
            }


        }

        )




        return bindingorders.root
    }

}