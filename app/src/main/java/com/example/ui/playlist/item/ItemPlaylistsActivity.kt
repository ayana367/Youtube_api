package com.example.ui.playlist.item

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.core.network.ext.ui.BaseActivity
import com.example.core.network.ext.result.Status
import com.example.core.network.ext.loadImage
import com.example.core.network.ext.showToast
import com.example.data.entity.model.ItemsItem
import com.example.databinding.ActivityItemPlaylistsBinding
import com.example.ui.playlist.PlaylistsActivity
import com.example.ui.playlist.videos.VideosActivity

class ItemPlaylistsActivity : BaseActivity<ItemViewModel, ActivityItemPlaylistsBinding>() {

    private lateinit var adapterPlaylist : AdapterItem
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: ItemViewModel by lazy {
        ViewModelProvider(this)[ItemViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityItemPlaylistsBinding{
        return ActivityItemPlaylistsBinding.inflate(layoutInflater)
    }


    override fun initViewModel() {
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
        }
        adapterPlaylist = AdapterItem(this::onClick)
        val  itemId = intent.getStringExtra(PlaylistsActivity.ID)

        binding.containerToolbar.tvBack.setOnClickListener{
            finish()
        }

        showToast(itemId.toString())
        viewModel.playlists(itemId.toString()).observe(this) {
            it.data?.items?.let { it1 -> adapterPlaylist.setItems(it1) }
            binding.tvTitle.text = intent.getStringExtra(PlaylistsActivity.KEY)
            binding.ivImage.loadImage(intent.getStringExtra(PlaylistsActivity.IMAGE).toString())
            when (it.status) {
                Status.SUCCESS -> {
                    binding.rvPlaylistItem.adapter = adapterPlaylist
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    showToast(it.message.toString())
                }
            }
        }
    }
    private fun onClick(item: ItemsItem){
        val i = Intent(this, VideosActivity::class.java)
        i.putExtra(DESC,item.snippet.description)
        i.putExtra(KEY,item.snippet.title)
        registerForActivity.launch(i)
    }
    companion object{
        const val DESC = "desc"
        const val KEY = "tit"
    }
}