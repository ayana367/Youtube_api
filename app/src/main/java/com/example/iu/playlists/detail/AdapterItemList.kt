package com.example.iu.playlists.detail
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.core.network.ext.result.ui.loadImage
import com.example.data.local.entity.remote.model.ItemsItem
import com.example.databinding.ItemPlaylistsBinding


class AdapterItem: RecyclerView.Adapter<AdapterItem.ViewHolder>() {

    private val itemsItem = arrayListOf<ItemsItem>()

    inner class ViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: ItemsItem) {
            binding.tvTitle.text = item.snippet.title
            binding.tvDesk.text = item.snippet.publishedAt
            binding.ivItem.loadImage(item.snippet.thumbnails.default.url)
            binding.constBar.isVisible = false
        }
    }

    fun setItems(list: List<ItemsItem>) {
        itemsItem.clear()
        itemsItem.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(itemsItem[position])
    }
}