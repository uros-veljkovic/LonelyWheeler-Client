package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.OfferItemBigBinding
import project.lonelywheeler.databinding.OfferItemSmallBinding
import project.lonelywheeler.db.entity.offfer.OfferEntity

class OfferItemBigRvAdapter(
    val listener: OnOfferItemClickListener,
) :
    RecyclerView.Adapter<OfferItemBigRvAdapter.OfferItemBigViewHolder>() {

    private var offerList: List<OfferEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferItemBigViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OfferItemBigBinding.inflate(inflater, parent, false)
        return OfferItemBigViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferItemBigViewHolder, position: Int) {
        holder.bind(offerList[position])
        holder.itemView.setOnClickListener { listener.onOfferItemClick(position) }
    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    fun setList(list: List<OfferEntity>) {
        this.offerList = list
        notifyDataSetChanged()
    }

    inner class OfferItemBigViewHolder constructor(val binding: OfferItemBigBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: OfferEntity) {
            binding.offer = offer
            binding.executePendingBindings()

        }
    }

    interface OnOfferItemClickListener {
        fun onOfferItemClick(position: Int)
    }
}