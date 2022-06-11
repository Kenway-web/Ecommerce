package eu.kenway.ecommerce.carttable

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cartData")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val userid:String,

    val Itemname:String,

    val  price:String,

    val quantity:String

)