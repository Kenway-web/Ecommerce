package eu.kenway.ecommerce.products

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class BelongingsDetails (

        val data : List<Belongings>
    )


    @Entity(tableName ="items")
    data class Belongings(

        @PrimaryKey(autoGenerate = true)
        val itemId:Int,

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