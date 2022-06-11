package eu.kenway.ecommerce.products.items.orders.models

import com.google.gson.annotations.SerializedName

data class OrdersData(
    val data:List<Order>
)
data class Order(
    @SerializedName("order id")
    val order_id:Int,
    @SerializedName("user id")
    val user_id:Int,
    @SerializedName("order date")
    val order_date:String,
    @SerializedName("order total")
    val order_total:Int,
    @SerializedName("data")
    val arrData:List<Items>
)
data class Items(
    @SerializedName("item name")
    val title:String,
    @SerializedName("desc")
    val desc:String,
    @SerializedName("price")
    val price:Int,
    @SerializedName("currency")
    val currency:String,
    @SerializedName("expiry date")
    val expiry_date:String,
    @SerializedName("quantity")
    val quantity:String
)
