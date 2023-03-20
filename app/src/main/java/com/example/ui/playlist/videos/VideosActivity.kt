package com.example.ui.playlist.videos

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.R
import com.example.core.network.ext.ui.BaseActivity
import com.example.core.network.ext.result.Status
import com.example.core.network.ext.showToast
import com.example.databinding.ActivityVideosBinding
import com.example.ui.playlist.PlaylistsActivity
import com.example.ui.playlist.item.ItemPlaylistsActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.material.button.MaterialButton


class VideosActivity : BaseActivity<VideoViewModel, ActivityVideosBinding>() {
    override val viewModel: VideoViewModel by lazy {
        ViewModelProvider(this)[VideoViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityVideosBinding {
        return ActivityVideosBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initView()
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
        }

        binding.containerToolbar.tvBack.setOnClickListener{
            finish()
        }

        val id = intent.getStringExtra(PlaylistsActivity.ID)
        viewModel.getVideo(id.toString()).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.tvTitle.text = intent.getStringExtra(ItemPlaylistsActivity.KEY)
                    binding.tvDescription.text = intent.getStringExtra(ItemPlaylistsActivity.DESC)
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
        Dialog()
        ExoPlayer()

    }

    @SuppressLint("MissingInflatedId")
    private fun Dialog() {
        binding.btnDownload.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.alertdialog_download, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()

            val btnDown = view.findViewById<MaterialButton>(R.id.btnM_down)
            btnDown.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun ExoPlayer() {
        val player = ExoPlayer.Builder(this).build()
        val mediaItem = MediaItem.fromUri(URL)
        player.setMediaItem(mediaItem)
        binding.playerView.player = player
    }
    companion object{
        const val URL = "https://html5videoformatconverter.com/data/images/happyfit2.mp4"
    }
}