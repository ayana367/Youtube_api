package com.example.iu.playlists

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.R
import com.example.core.network.ext.result.ui.BaseActivity
import com.example.core.network.ext.result.ui.Status
import com.example.core.network.ext.result.ui.isNetworkConnected
import com.example.core.network.ext.result.ui.showToast
import com.example.databinding.PlayalistsMainBinding
import com.example.data.local.entity.remote.model.ItemsItem
import com.example.iu.playlists.detail.ItemPlaylistsActivity
import com.example.util.InternetConnectivity


class PlaylistsActivity : BaseActivity<PlaylistsViewModel,PlayalistsMainBinding>() {

    private lateinit var adapterPlaylist : AdapterPlaylist
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater):PlayalistsMainBinding {
        return PlayalistsMainBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
        }
        viewModel.playlists().observe(this){
            adapterPlaylist = AdapterPlaylist(it,this::itemClick)
            when(it.status){
                Status.SUCCESS -> {
                    binding.recyclerMain.adapter = adapterPlaylist
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR ->{
                    viewModel.loading.postValue(true)
                    showToast(it.message.toString())
                }
            }
        }
    }

    override fun initListener() {
        binding.noInternet.btnTryAgain.setOnClickListener{
            showToast(getString(R.string.no_connection))
        }
    }

    private fun itemClick(itemsItem: ItemsItem) {
        val i = Intent(this, ItemPlaylistsActivity::class.java)
        i.putExtra(KEY,itemsItem.id)
        registerForActivity.launch(i)
    }

    override fun checkInternet() {
        super.checkInternet()
        val connectivity = InternetConnectivity(application)
        connectivity.observe(this) {
            if (!it) {
                binding.noInternet.root.isVisible = true

                binding.noInternet.btnTryAgain.setOnClickListener {
                    if (!isNetworkConnected()) {
                        showToast(getString(R.string.no_connection))
                    } else {
                        binding.noInternet.root.isVisible = false
                    }
                }
            } else {
                initViewModel()
            }
        }
    }

    companion object{
        const val KEY = "key"
    }
}