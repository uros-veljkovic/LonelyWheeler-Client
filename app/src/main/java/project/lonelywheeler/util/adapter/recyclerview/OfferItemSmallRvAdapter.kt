package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.OfferItemSmallBinding
import project.lonelywheeler.db.entity.offer.OfferEntity

class OfferItemSmallRvAdapter(
    val listener: OnOfferItemClickListener,
) :
    RecyclerView.Adapter<OfferItemSmallRvAdapter.OfferItemSmallViewHolder>() {

    private var previewedOfferList: MutableList<OfferEntity> = mutableListOf()
    private var fullOfferList: MutableList<OfferEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferItemSmallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OfferItemSmallBinding.inflate(inflater)
        return OfferItemSmallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferItemSmallViewHolder, position: Int) {
        holder.bind(previewedOfferList[position])
        holder.itemView.setOnClickListener { listener.onOfferItemClick(position) }
    }

    override fun getItemCount(): Int {
        return previewedOfferList.size
    }

    fun setList(list: MutableList<OfferEntity>) {
        this.fullOfferList = list
        this.previewedOfferList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun getPreviewedList(): List<OfferEntity> {
        return previewedOfferList
    }

    fun getFullList(): List<OfferEntity> {
        return fullOfferList
    }

    fun filterPreviewedList(list: MutableList<OfferEntity>) {
        this.previewedOfferList = list
        notifyDataSetChanged()
    }

    inner class OfferItemSmallViewHolder constructor(val binding: OfferItemSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: OfferEntity) {
            binding.offer = offer
        }
    }

    interface OnOfferItemClickListener {
        fun onOfferItemClick(position: Int)
    }
}