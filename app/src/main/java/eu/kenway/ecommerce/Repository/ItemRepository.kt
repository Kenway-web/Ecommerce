package eu.kenway.items.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.kenway.items.api.ItemService
import eu.kenway.items.models.ItemList


class ItemRepository(private val itemService: ItemService) {

    private val itemLiveData=MutableLiveData<ItemList>()

    val items:LiveData<ItemList>
        get() = itemLiveData

    suspend fun getItems(){
        val result=itemService.getItems()
        if(result?.body()!=null)
        {
          itemLiveData.postValue(result.body())
        }
    }
}