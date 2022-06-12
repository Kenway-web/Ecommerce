package eu.kenway.ecommerce.products.items.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.kenway.ecommerce.products.items.home.ItemRepository
import eu.kenway.items.models.ItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: ItemRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getItems()
        }

    }

    val items:LiveData<ItemList>
    get() = repository.items
}