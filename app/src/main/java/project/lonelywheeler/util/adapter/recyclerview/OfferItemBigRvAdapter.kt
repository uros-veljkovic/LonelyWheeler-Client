package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.OfferItemBigBinding
import project.lonelywheeler.db.entity.offer.OfferEntity
import project.lonelywheeler.util.constants.ACTION_READ_PERSONAL_OFFERS

class OfferItemBigRvAdapter(
    val listener: OnMyOfferItemClickListener,
    val sellerId: String,
) :
    RecyclerView.Adapter<OfferItemBigRvAdapter.OfferItemBigViewHolder>() {

    private var offerList: MutableList<OfferEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferItemBigViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OfferItemBigBinding.inflate(inflater, parent, false)
//        hideButtonsIfNotSellersOffers(binding)
        return OfferItemBigViewHolder(binding)
    }

    private fun hideButtonsIfNotSellersOffers(binding: OfferItemBigBinding) {
        if (sellerId != ACTION_READ_PERSONAL_OFFERS) {
            binding.apply {
                btnDeleteOffer.visibility = View.GONE
                btnEditOffer.visibility = View.GONE
            }
        }
    }

    override fun onBindViewHolder(holder: OfferItemBigViewHolder, position: Int) {
        holder.bind(offerList[position])
        holder.binding.apply {
            if (offerList[position].sellerId != sellerId) {
                btnEditOffer.visibility = View.GONE
                btnDeleteOffer.visibility = View.GONE
            } else {
                btnEditOffer.visibility = View.VISIBLE
                btnDeleteOffer.visibility = View.VISIBLE
            }
            btnEditOffer.setOnClickListener { listener.onUpdate(position) }
            btnDeleteOffer.setOnClickListener { listener.onDelete(position) }
        }

    }

    override fun getItemCount(): Int {
        return offerList.size
    }

    fun getItem(position: Int): OfferEntity {
        return offerList[position]
    }

    fun deleteItem(position: Int): OfferEntity {
        val deletedEntity = offerList.removeAt(position)
        notifyDataSetChanged()
        return deletedEntity
    }

    fun setList(list: List<OfferEntity>) {
        this.offerList = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class OfferItemBigViewHolder constructor(val binding: OfferItemBigBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: OfferEntity) {
            binding.offer = offer
            binding.executePendingBindings()

        }
    }

    interface OnMyOfferItemClickListener {
        fun onUpdate(position: Int)
        fun onDelete(position: Int)
    }
}