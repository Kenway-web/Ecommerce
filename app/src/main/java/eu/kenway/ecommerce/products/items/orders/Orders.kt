package eu.kenway.ecommerce.products.items.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentOrdersBinding
import eu.kenway.ecommerce.products.items.orders.models.OrdersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class orders : Fragment() {

    private lateinit var adapter: OrdersAdapter


    private lateinit var yourOrdersViewModel: YourOrdersViewModel
    private lateinit var _binding:FragmentOrdersBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        yourOrdersViewModel =
            ViewModelProvider(this).get(YourOrdersViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.title = "Orders"

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val view: View = binding.root
        val recycler=view.findViewById<RecyclerView>(R.id.recycler)
        val retrofitInterface=OrdersRetrofitInterface.create().getProducts()
        retrofitInterface.enqueue(object : Callback<OrdersData> {
            override fun onResponse(call: Call<OrdersData>, response: Response<OrdersData>) {
                if (response.body()!=null){
                    val orders=response.body()!!.data[0].arrData
                    adapter=OrdersAdapter(orders)
                    recycler.adapter=adapter
                }
            }

            override fun onFailure(call: Call<OrdersData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })



        return view
    }


}