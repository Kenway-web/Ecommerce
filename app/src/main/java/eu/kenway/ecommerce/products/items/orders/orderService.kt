package eu.kenway.ecommerce.products.items.orders


import retrofit2.Response
import retrofit2.http.POST

interface orderService {

    @POST("getorders")
    suspend fun getItems(): Response<OrdersData>

    //BASEURL +QUOTES

}