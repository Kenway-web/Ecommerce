package eu.kenway.items.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.kenway.items.Repository.ItemRepository

class Mainviewmodelfactory(private val repository: ItemRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return  MainViewModel(repository) as T
    }
}