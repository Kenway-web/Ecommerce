package eu.kenway.ecommerce.products.items.home



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.saMPLEACTIVITY
import eu.kenway.items.models.Data


class ProductsAdapter(val context:Context,private val belongings :  List<Data>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val title= item.findViewById<TextView>(R.id.title)!!
        val price= item.findViewById<TextView>(R.id.price)!!
        val currency = item.findViewById<TextView>(R.id.currency)!!
        val description = item.findViewById<TextView>(R.id.description)!!
        val quantity = item.findViewById<TextView>(R.id.quantity)!!
        val expiry = item.findViewById<TextView>(R.id.expiry)!!
        val image = item.findViewById<ImageView>(R.id.imageView4)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        val returnview = ProductViewHolder(view)
        view.setOnClickListener{

            val bundle = Bundle()
            bundle.putString("title", belongings[returnview.adapterPosition].title)
            bundle.putString("price", belongings[returnview.adapterPosition].price.toString())
            bundle.putString("quantity", belongings[returnview.adapterPosition].quantity)
            bundle.putString("expirydate", belongings[returnview.adapterPosition].expiry_date)

            val intent = Intent(view.context, saMPLEACTIVITY::class.java)
            intent.putExtras(bundle)
            view.context.startActivity(intent)
           /* val bundle = Bundle()
            bundle.putString("title", belongings[returnview.adapterPosition].title)
            bundle.putString("price", belongings[returnview.adapterPosition].price.toString())
            bundle.putString("quantity", belongings[returnview.adapterPosition].quantity)
            bundle.putString("expirydate", belongings[returnview.adapterPosition].expiry_date)*/

            //val dialog = BelongingsFragment()//The fragment that u want to open for example
            //dialog.arguments=bundle
             //(context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.framelayout,BelongingsFragment())
               //  .commit()



        }

        return returnview
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val array = belongings[position]
        if(array.description.isEmpty()){
            holder.description.text="Un available"
        }
        else{
            holder.description.text=array.description
        }
        //  Log.i("check","${array.title}")
        holder.title.text=array.title
        holder.price.text="$"+array.price.toString()
        holder.currency.text=array.currency
        holder.description.text=array.description
        holder.expiry.text=array.expiry_date
        holder.quantity.text=array.quantity
        holder.image.setImageResource(
            when(array.title){
                "Bread" -> R.drawable.product_01
                "Milk" -> R.drawable.product_02
                "Rice" -> R.drawable.product_03
                "Salt" -> R.drawable.product_04
                "Sunflower" -> R.drawable.sunflower
                "Sugar" -> R.drawable.sugar
                else ->R.drawable.sunflower
            }
        )

    }

    override fun getItemCount(): Int {
        return belongings.size
    }


}