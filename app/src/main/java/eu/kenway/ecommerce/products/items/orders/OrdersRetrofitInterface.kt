package eu.kenway.ecommerce.products.items.orders

import eu.kenway.ecommerce.products.items.orders.models.OrdersData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.PUT

interface OrdersRetrofitInterface {


    @POST("getorders")
    fun getProducts(): Call<OrdersData>

    companion object{
        var BASE_URL="https://demo9990262.mockable.io/"

        fun create():OrdersRetrofitInterface{
            val retrofit= Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(OrdersRetrofitInterface::class.java)
        }
    }
}