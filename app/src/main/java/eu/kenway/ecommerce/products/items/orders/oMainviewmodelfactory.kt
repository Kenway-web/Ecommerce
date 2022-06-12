package eu.kenway.ecommerce.products.items.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class oMainviewmodelfactory(private val repository: orderRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return  oMainViewModel(repository) as T
    }
}