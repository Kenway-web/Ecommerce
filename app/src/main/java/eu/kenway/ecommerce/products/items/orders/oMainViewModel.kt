package eu.kenway.ecommerce.products.items.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class oMainViewModel(val repository: orderRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getItems()
        }

    }

    val items:LiveData<OrdersData>
    get() = repository.items
}