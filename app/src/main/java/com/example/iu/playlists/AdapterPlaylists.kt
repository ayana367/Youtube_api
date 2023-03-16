package com.example.iu.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.network.ext.result.ui.Resource
import com.example.core.network.ext.result.ui.loadImage
import com.example.databinding.ItemPlaylistsBinding
import com.example.data.local.entity.remote.model.ItemsItem
import com.example.data.local.entity.remote.model.Playlists

class AdapterPlaylist(private val playlists: Resource<Playlists>, private val onClick:(ItemsItem)->Unit):RecyclerView.Adapter<AdapterPlaylist.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPlaylistsBinding):RecyclerView. ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: ItemsItem) {
            binding.tvTitle.text = item.snippet.title
            binding.tvDesk.text = item.contentDetails.itemCount.toString()+ " video series"
            binding.ivItem.loadImage(item.snippet.thumbnails.default.url)
            itemView.setOnClickListener {
                onClick(item)
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

