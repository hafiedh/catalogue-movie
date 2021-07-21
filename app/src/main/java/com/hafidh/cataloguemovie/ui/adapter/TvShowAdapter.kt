package com.hafidh.cataloguemovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidh.cataloguemovie.databinding.ItemDataBinding
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.utils.Constans
import com.hafidh.cataloguemovie.utils.SetOnClickListener

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {
    private var onItemClickListener: SetOnClickListener? = null
    private var list: List<TvEntity> = emptyList()

    inner class TvViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun tvBind(data: TvEntity) {
            with(binding) {
                Glide.with(itemView)
                    .load("${Constans.BASE_IMAGE}${data.poster}")
                    .into(img)
                tvItemList.text = data.name
            }
        }

        init {
            binding.cvDataList.setOnClickListener { view ->
                onItemClickListener?.onClicked(view, layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder =
        TvViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.tvBind(list[position])

    override fun getItemCount(): Int = list.size

    fun addList(data: List<TvEntity>) {
        this.list = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClicked: SetOnClickListener) {
        this.onItemClickListener = onItemClicked
    }

    fun getData(): List<TvEntity> = list
}