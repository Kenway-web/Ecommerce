package eu.kenway.ecommerce.carttable

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.kenway.ecommerce.R
import org.w3c.dom.Text

class CartAdapater(private val context: Context,
                   private val listener: Icartvadapter) : RecyclerView.Adapter<CartAdapater.Viewholder>() {

    val itemslist=ArrayList<CartEntity>()
    class Viewholder(view: View):RecyclerView.ViewHolder(view) {
        val titletxt=view.findViewById<TextView>(R.id.title)
        val pricetxt=view.findViewById<TextView>(R.id.price)
        val quentitytxt=view.findViewById<TextView>(R.id.quantity)
        val deletebtn=view.findViewById<Button>(R.id.deleteBtn)
        val imageitem=view.findViewById<ImageView>(R.id.itemImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val viewholder=Viewholder( LayoutInflater.from(context).inflate(R.layout.cartdetails,parent,false))
         viewholder.deletebtn.setOnClickListener {
          listener.onItemClicked(itemslist[viewholder.adapterPosition])
      }
        return viewholder
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val datalist=itemslist[position]

        holder.titletxt.text=datalist.Itemname
        holder.pricetxt.text=datalist.price
        holder.quentitytxt.text=datalist.quantity
        when(datalist.Itemname.toString())
        {
            "Bread"-> holder.imageitem.setImageResource(R.drawable.product_01)
            "Sugar"-> holder.imageitem.setImageResource(R.drawable.sugar)
            "Sunflower Oil"-> holder.imageitem.setImageResource(R.drawable.sunflower)
            "Rice"->holder.imageitem.setImageResource(R.drawable.product_03)
            "Salt"->holder.imageitem.setImageResource(R.drawable.product_04)
            else ->holder.imageitem.setImageResource(R.drawable.product_02)
        }
      //  holder.deletebtn.setOnClickListener {
        //    cartViewModel.deleteItem(datalist)
        //}
    }

    override fun getItemCount(): Int {
        return itemslist.size
    }

    fun updatelist(newlist:List<CartEntity>)
    {
      itemslist.clear()
        itemslist.addAll(newlist)
        notifyDataSetChanged()


    }
}