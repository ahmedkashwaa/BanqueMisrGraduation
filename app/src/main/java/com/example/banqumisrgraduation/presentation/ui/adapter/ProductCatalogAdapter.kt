import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.data.model.Products
import com.example.banqumisrgraduation.presentation.ui.activity.CoffeBuyActivity

class ProductCatalogAdapter :
    ListAdapter<Products, ProductCatalogAdapter.ViewHolder>(ProductsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_list, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.image)
        val coffe = itemView.findViewById<TextView>(R.id.name)
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CoffeBuyActivity::class.java)
                intent.putExtra("coffeName",currentList.get(adapterPosition).name)
                intent.putExtra("coffePhoto",currentList.get(adapterPosition).imgString)
                intent.putExtra("coffeID",currentList.get(adapterPosition).id)
                intent.putExtra("Price",currentList.get(adapterPosition).price?.toDouble())
                intent.putExtra("Count",currentList.get(adapterPosition).boughtItemsCount)
                intent.putExtra("Size",currentList.get(adapterPosition).size)
                intent.putExtra("Sugar",currentList.get(adapterPosition).sugar)
                itemView.context.startActivity(intent)



            }
        }


        fun bind(item: Products) = with(itemView) {
            coffe.text = item.name
            Glide.with(context)
                .load(item.imgString)
                .into(image)

        }
    }


}

class ProductsDiffCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}