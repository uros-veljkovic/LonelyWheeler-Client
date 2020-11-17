package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.OfferItemBigBinding
import project.lonelywheeler.databinding.OfferItemSmallBinding
import project.lonelywheeler.db.entity.offer.OfferEntity

class AllOfferRecViewAdapter(
    val listener: OnOfferItemClickListener,
) :
    RecyclerView.Adapter<AllOfferRecViewAdapter.AllOfferViewHolder>() {

    private var offerList: List<OfferEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOfferViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OfferItemSmallBinding.inflate(inflater)
        return AllOfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllOfferViewHolder, position: Int) {
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

    inner class AllOfferViewHolder constructor(val binding: OfferItemSmallBinding) :
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