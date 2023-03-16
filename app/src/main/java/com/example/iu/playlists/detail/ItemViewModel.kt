package com.example.iu.playlists.detail

import androidx.lifecycle.LiveData
import com.example.App
import com.example.core.network.ext.result.ui.BaseViewModel
import com.example.core.network.ext.result.ui.Resource
import com.example.data.local.entity.remote.model.Playlists

class ItemViewModel : BaseViewModel() {
    fun playlists(playlistId: String): LiveData<Resource<Playlists>> {
        return App.repository.getItemList(playlistId)
    }
}