package eu.kenway.items.api


import eu.kenway.items.models.ItemList
import retrofit2.Response
import retrofit2.http.POST

interface ItemService {

    @POST("login")
    suspend fun getItems(): Response<ItemList>

    //BASEURL +QUOTES

}