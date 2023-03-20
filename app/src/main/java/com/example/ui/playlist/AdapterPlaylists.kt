package com.example.ui.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.network.ext.loadImage
import com.example.databinding.ItemPlaylistsBinding
import com.example.data.entity.model.ItemsItem
import com.example.data.entity.model.Playlists
import com.example.core.network.ext.result.Resource

class AdapterPlaylist(private val playlists: Resource<Playlists>, private val onClick:(String, ItemsItem)->Unit):RecyclerView.Adapter<AdapterPlaylist.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPlaylistsBinding):RecyclerView. ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: ItemsItem) {
            binding.tvTitle.text = item.snippet.title
            binding.tvDesk.text = item.contentDetails.itemCount.toString()+ " video series"
            binding.ivItem.loadImage(item.snippet.thumbnails.default.url)
            itemView.setOnClickListener {
                onClick(binding.tvTitle.text.toString(),item)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlaylistsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = playlists.data?.items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playlists.data?.items!![position])
    }
}

