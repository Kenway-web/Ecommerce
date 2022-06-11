package eu.kenway.ecommerce.products.items.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.products.items.orders.models.Items

class OrdersAdapter(private val orders: List<Items>) : RecyclerView.Adapter<OrdersAdapter.YourOrderViewHolder>() {

    class YourOrderViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val title=item.findViewById<TextView>(R.id.title)
        val price=item.findViewById<TextView>(R.id.price)
        val currency=item.findViewById<TextView>(R.id.currency)
        val description=item.findViewById<TextView>(R.id.description)
        val quantity=item.findViewById<TextView>(R.id.quantity)
        val expiry=item.findViewById<TextView>(R.id.expiry)
        val image=item.findViewById<ImageView>(R.id.imageView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourOrderViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.custom_orders,parent,false)
        val returnView= YourOrderViewHolder(view)
        return returnView
    }

    override fun onBindViewHolder(holder: YourOrderViewHolder, position: Int) {
        val positionin=orders[position]
        holder.title.text=positionin.title
        holder.price.text=positionin.price.toString()
        holder.currency.text=positionin.currency
        holder.description.text=positionin.desc
        holder.expiry.text=positionin.expiry_date
        holder.quantity.text=positionin.quantity
        holder.image.setImageResource(
            when(positionin.title){
                "Bread" -> R.drawable.product_01
                "Milk"  -> R.drawable.product_02
                "Rice"  -> R.drawable.product_03
                "Salt"  -> R.drawable.product_04
                "Sunflower" -> R.drawable.sunflower
                "Sugar"  -> R.drawable.sugar
                else ->R.drawable.sunflower
            }
        )
    }

    override fun getItemCount(): Int {
        return orders.size
    }

}