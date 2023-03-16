package com.example.iu.playlists

import androidx.lifecycle.LiveData
import com.example.App.Companion.repository
import com.example.core.network.ext.result.ui.BaseViewModel
import com.example.core.network.ext.result.ui.Resource
import com.example.data.local.entity.remote.model.Playlists

    class   PlaylistsViewModel: BaseViewModel() {

        fun playlists() : LiveData<Resource<Playlists>>{
            return repository.getPlaylists()
        }
    }