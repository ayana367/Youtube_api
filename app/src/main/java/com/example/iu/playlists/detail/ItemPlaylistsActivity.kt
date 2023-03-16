package com.example.iu.playlists.detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.core.network.ext.result.ui.BaseActivity
import com.example.core.network.ext.result.ui.Status
import com.example.core.network.ext.result.ui.showToast
import com.example.databinding.ActivityItemPlaylistsBinding
import com.example.iu.playlists.PlaylistsActivity

class ItemPlaylistsActivity : BaseActivity<ItemViewModel, ActivityItemPlaylistsBinding>() {

    private lateinit var adapterPlaylist : AdapterItem
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
        adapterPlaylist = AdapterItem()
        val  aa =intent.getStringExtra(PlaylistsActivity.KEY)
        Toast.makeText(this, "$aa", Toast.LENGTH_SHORT).show()
        viewModel.playlists(aa.toString()).observe(this) {
            it.data?.items?.let { it1 -> adapterPlaylist.setItems(it1) }
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
}