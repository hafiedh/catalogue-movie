package com.hafidh.cataloguemovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.ItemDataBinding
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.utils.Constans.BASE_IMAGE
import com.hafidh.cataloguemovie.utils.SetOnClickListener

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var onItemClickLisner: SetOnClickListener? = null
    private var list: List<MovieEntity> = emptyList()

    inner class MovieViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun movieBind(data: MovieEntity) {
            with(binding) {
                Glide.with(itemView)
                    .load("${BASE_IMAGE}${data.poster}")
                    .error(R.drawable.ic_loading)
                    .into(img)
                tvItemList.text = data.title
            }
        }
        init {
            binding.cvDataList.setOnClickListener { view->
                onItemClickLisner?.onClicked(view,layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.movieBind(list[position])

    override fun getItemCount(): Int = list.size

    fun addList(data: List<MovieEntity>) {
        this.list = data
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClicked: SetOnClickListener){
        this.onItemClickLisner = onItemClicked
    }

    fun getData(): List<MovieEntity> = list

}