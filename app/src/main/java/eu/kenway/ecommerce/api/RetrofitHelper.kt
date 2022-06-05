package eu.kenway.items.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

   private val BASE_URL = "https://demo9990262.mockable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build()

    }

}