import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.banqumisrgraduation.presentation.ui.activity.CoffeBuyActivity
import kotlin.reflect.KFunction0

class CartAdapter(private val onPriceChangeCallBack: ()->Unit) : ListAdapter<Products, CartAdapter.ViewHolder>(ProductDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

  @SuppressLint("NotifyDataSetChanged")
  inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val Image = itemView.findViewById<ImageView>(R.id.cart_item_iv)
      val itemName = itemView.findViewById<TextView>(R.id.cart_item_name_tv)
      val price = itemView.findViewById<TextView>(R.id.cart_item_price_tv)
      val count = itemView.findViewById<TextView>(R.id.cart_item_count_tv)
      val increase = itemView.findViewById<Button>(R.id.cart_increase_count_ib)
      val decrease = itemView.findViewById<Button>(R.id.cart_decrease_count_ib)
      val size_cart = itemView.findViewById<TextView>(R.id.size_cart)

      init {
          increase.setOnClickListener {
              var single = (currentList.get(adapterPosition).price!!/currentList.get(adapterPosition).boughtItemsCount!!)
              currentList.get(adapterPosition).price = currentList.get(adapterPosition).price!!+single
              currentList.get(adapterPosition).boughtItemsCount=  currentList.get(adapterPosition).boughtItemsCount!!+1
              notifyDataSetChanged()
              onPriceChangeCallBack()

          }
          decrease.setOnClickListener {
              var single = (currentList.get(adapterPosition).price!!/currentList.get(adapterPosition).boughtItemsCount!!)
              if (currentList.get(adapterPosition).boughtItemsCount!!>1) {
                  currentList.get(adapterPosition).price = currentList.get(adapterPosition).price!!-single
                  currentList.get(adapterPosition).boughtItemsCount =
                      currentList.get(adapterPosition).boughtItemsCount!! - 1
              }
              notifyDataSetChanged()
              onPriceChangeCallBack()

          }
          itemView.setOnClickListener {
              val intent = Intent(itemView.context, CoffeBuyActivity::class.java)
              intent.putExtra("coffeID",currentList.get(adapterPosition).id)
              itemView.context.startActivity(intent)
          }

      }
        fun bind(item: Products) = with(itemView) {
            Glide.with(context)
                .load(item.imgString)
                .into(Image)
            itemName.text = item.name
            price.text = "${item.price.toString()} EGP"
            count.text= item.boughtItemsCount.toString()
            size_cart.text = when(item.size){
                1-> "Size : S"
                2-> "Size : M"
                3-> "Size : L"
                else -> {"Size : S"}
            }
        }
    }


}

class ProductDiffCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
                return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}