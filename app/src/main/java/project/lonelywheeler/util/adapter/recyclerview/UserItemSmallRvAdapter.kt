package project.lonelywheeler.util.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.databinding.OfferItemBigBinding
import project.lonelywheeler.databinding.UserItemSmallBinding
import project.lonelywheeler.db.entity.user.UserEntity

class UserItemSmallRvAdapter(
    val listener: OnOfferItemClickListener,
) :
    RecyclerView.Adapter<UserItemSmallRvAdapter.UserItemSmallViewHolder>() {

    private var displayedList: MutableList<UserEntity> = mutableListOf()
    private var fullList: MutableList<UserEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemSmallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemSmallBinding.inflate(inflater)
        return UserItemSmallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemSmallViewHolder, position: Int) {
        holder.bind(displayedList[position])
        holder.itemView.setOnClickListener { listener.onOfferItemClick(position) }
    }

    override fun getItemCount(): Int {
        return displayedList.size
    }

    fun setList(list: MutableList<UserEntity>) {
        this.fullList = list
        this.displayedList = list
        notifyDataSetChanged()
    }

    fun getDisplayedList(): List<UserEntity> {
        return displayedList
    }

    fun getFullList(): List<UserEntity> {
        return fullList
    }

    fun filterDisplayedList(list: MutableList<UserEntity>) {
        this.displayedList = list
        notifyDataSetChanged()
    }

    inner class UserItemSmallViewHolder constructor(val binding: UserItemSmallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userEntity: UserEntity) {
            binding.userEntity = userEntity
            binding.executePendingBindings()
            binding.notifyChange()
        }
    }

    interface OnOfferItemClickListener {
        fun onOfferItemClick(position: Int)
    }
}