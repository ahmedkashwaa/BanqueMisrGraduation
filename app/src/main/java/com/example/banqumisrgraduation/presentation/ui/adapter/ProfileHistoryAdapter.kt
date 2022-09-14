import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.data.model.Products
import java.util.*

class ProfileHistoryAdapter :
    ListAdapter<Products, ProfileHistoryAdapter.ViewHolder>(OrdersDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Image = itemView.findViewById<ImageView>(R.id.cart_item_iv)
        val itemName = itemView.findViewById<TextView>(R.id.cart_item_name_tv)
        val pricee = itemView.findViewById<TextView>(R.id.cart_item_price_tv)
        val count = itemView.findViewById<TextView>(R.id.cart_item_count_tv)
        val increase = itemView.findViewById<Button>(R.id.cart_increase_count_ib)
        val decrease = itemView.findViewById<Button>(R.id.cart_decrease_count_ib)
        val size_cart = itemView.findViewById<TextView>(R.id.size_cart)
        init {
            increase.visibility = View.GONE
            decrease.visibility= View.GONE
        }
        fun bind(item: Products) = with(itemView) {
            itemName.text = item.name
            Glide.with(context)
                .load(item.imgString)
                .into(Image)
            pricee.text = "${item.price.toString()} EGP"
            count.text = item.boughtItemsCount.toString()
            size_cart.text = when(item.size){
                1-> "Size : S"
                2-> "Size : M"
                3-> "Size : L"
                else -> {"Size : S"}
            }
        }
    }


}

class OrdersDiffCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        //    example  return oldItem.nightId == newItem.nightId
// check with any value of the item
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}