package eu.kenway.ecommerce.api


import retrofit2.Response
import retrofit2.http.POST

interface Qservice {

    var message:String

    @POST("login")
    suspend fun getQRandom():Response<BelongingsDetails>

}
