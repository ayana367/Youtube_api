package com.example.ui.playlist

import androidx.lifecycle.LiveData
import com.example.App.Companion.repository
import com.example.core.network.ext.result.Resource
import com.example.core.network.ext.ui.BaseViewModel
import com.example.data.entity.model.Playlists

    class   PlaylistsViewModel: BaseViewModel() {
        fun playlists() : LiveData<Resource<Playlists>>{
            return repository.getPlaylists()
        }
    }