package eu.kenway.ecommerce.products.items.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData



class orderRepository(private val orderService: orderService) {

    private val itemLiveData=MutableLiveData<OrdersData>()

    val items:LiveData<OrdersData>
        get() = itemLiveData

    suspend fun getItems(){
        val result=orderService.getItems()
        if(result?.body()!=null)
        {
          itemLiveData.postValue(result.body())
        }
    }
}