package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.ProductItemBinding
import project.lonelywheeler.db.entity.product.ProductEntity

class AllOfferRecViewAdapter(
    val listener: OnOfferItemClickListener
) :
    RecyclerView.Adapter<AllOfferRecViewAdapter.AllOfferViewHolder>() {

    private var productList: List<ProductEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOfferViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater)
        return AllOfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllOfferViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.itemView.setOnClickListener { listener.onOfferItemClick(position) }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setList(list: List<ProductEntity>) {
        this.productList = list
        notifyDataSetChanged()
    }

    inner class AllOfferViewHolder constructor(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(product: ProductEntity) {
            binding.product = product
            binding.executePendingBindings()

        }
    }

    interface OnOfferItemClickListener {
        fun onOfferItemClick(position: Int)
    }
}