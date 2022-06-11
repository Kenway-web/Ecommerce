package eu.kenway.ecommerce.carttable

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) :AndroidViewModel(application ) {

    private val readallData:LiveData<List<CartEntity>>
    private val repository:cartRepository

    init {
        val itemDao=CartDatabase.getDatabase(application).itemDao()
        repository=cartRepository(itemDao)
        readallData=repository.readalldata
    }

    fun addItem(entity: CartEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.additem(entity)
        }
    }
    fun deleteItem(entity: CartEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteitem(entity)
        }
    }
    fun userDataUsername(userid:String): LiveData<List<CartEntity>> {
        return repository.userDataUsername(userid)
    }

}