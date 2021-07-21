package com.hafidh.cataloguemovie.ui.fragment.favorite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidh.cataloguemovie.R
import com.hafidh.cataloguemovie.databinding.ItemDataBinding
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.activity.DetailActivity
import com.hafidh.cataloguemovie.utils.Constans
import com.hafidh.cataloguemovie.utils.Constans.EXTRA_ID_MOVIE

class AdapterMovie(private val context: Context) : PagedListAdapter<MovieEntity,AdapterMovie.ViewHolder>(diffUtils) {

    inner class ViewHolder(private val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MovieEntity){
            with(binding){
                Glide.with(itemView)
                    .load("${Constans.BASE_IMAGE}${data.poster}")
                    .error(R.drawable.ic_loading)
                    .into(img)
                tvItemList.text = data.title
                cvDataList.setOnClickListener {
                    Intent(context,DetailActivity::class.java).also {
                        it.putExtra(EXTRA_ID_MOVIE,data.id)
                        context.startActivity(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val data = getItem(position)
        if (data != null){
            holder.bind(data)
        }
    }

    companion object{
        private val diffUtils = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean = oldItem == newItem
        }
    }
}