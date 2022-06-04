package eu.kenway.ecommerce.api

import com.google.gson.annotations.SerializedName

data class BelongingsDetails (

        val data : List<Belongings>
    )

    data class Belongings(
        @SerializedName("item name")
        val title: String,

        @SerializedName("image")
        val image : String,

        @SerializedName("desc")
        val description : String,

        @SerializedName("price")
        val price : Double,

        @SerializedName("currency")
        val currency : String,

        @SerializedName("expiry date")
        val expiry_date : String,

        @SerializedName("quantity")
        val quantity : String
    )