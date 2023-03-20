package com.example.ui.playlist.item

import androidx.lifecycle.LiveData
import com.example.App
import com.example.core.network.ext.result.Resource
import com.example.core.network.ext.ui.BaseViewModel
import com.example.data.entity.model.Playlists

class ItemViewModel : BaseViewModel() {
    fun playlists(playlistId: String): LiveData<Resource<Playlists>> {
        return App.repository.getItemList(playlistId)
    }
}