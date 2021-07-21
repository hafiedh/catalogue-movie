package com.hafidh.cataloguemovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidh.cataloguemovie.databinding.ItemDataBinding
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.utils.Constans
import com.hafidh.cataloguemovie.utils.SetOnClickListener

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {
    private var onItemClickLisner: SetOnClickListener? = null
    private var list: List<ItemData> = emptyList()

    inner class TvViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun tvBind(data: ItemData) {
            with(binding) {
                Glide.with(itemView)
                    .load("${Constans.BASE_IMAGE}${data.poster}")
                    .into(img)
                tvItemList.text = data.name
            }
        }

        init {
            binding.cvDataList.setOnClickListener { view ->
                onItemClickLisner?.onClicked(view, layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder =
        TvViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.tvBind(list[position])

    override fun getItemCount(): Int = list.size

    fun addList(data: List<ItemData>) {
        this.list = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClicked: SetOnClickListener) {
        this.onItemClickLisner = onItemClicked
    }

    fun getData(): List<ItemData> = list
}