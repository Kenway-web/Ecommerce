package eu.kenway.ecommerce.products.items.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Mainviewmodelfactory(private val repository: ItemRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return  MainViewModel(repository) as T
    }
}