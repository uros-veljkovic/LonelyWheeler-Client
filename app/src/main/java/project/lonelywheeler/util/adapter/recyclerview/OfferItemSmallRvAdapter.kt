package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.OfferItemSmallBinding
import project.lonelywheeler.db.entity.offfer.OfferEntity

class OfferItemSmallRvAdapter(
    val listener: OnOfferItemClickListener,
) :
    RecyclerView.Adapter<OfferItemSmallRvAdapter.OfferItemSmallViewHolder>() {

    private var offerList: List<OfferEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferItemSmallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OfferItemSmallBinding.inflate(inflater)
        return OfferItemSmallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferItemSmallViewHolder, position: Int) {
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

    inner class OfferItemSmallViewHolder constructor(val binding: OfferItemSmallBinding) :
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