package eu.kenway.ecommerce.products

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import eu.kenway.ecommerce.databinding.EachItemBinding

class ProductsAdapter(private val belongings:List<Belongings>): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    private lateinit var binding:EachItemBinding



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsAdapter.ProductViewHolder {
       val inflater=LayoutInflater.from(parent.context)
        binding= EachItemBinding.inflate(inflater,parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ProductViewHolder, position: Int) {
       holder.bind(belongings[position])
    }

    override fun getItemCount(): Int {
      return belongings.size
    }

   inner class ProductViewHolder(item: EachItemBinding) : RecyclerView.ViewHolder(item.root){

        fun bind(item: Belongings)
        {
            binding.apply {
                title.text=item.title
                    .toString()
                price.text=item.price.toString()
                currency.text=item.currency.toString()
                description.text=item.description.toString()
                quantity.text=item.quantity.toString()
                expiry.text=item.expiry_date.toString()

            }

        }


    }
}