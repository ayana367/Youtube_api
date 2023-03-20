package com.example.data.remote

import com.example.core.network.ext.BaseDataSource
import com.example.BuildConfig
import com.example.core.network.ext.RetrofitClient


class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()

    }

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(
            "snippet,contentDetails",
            "UCJuMbdKSMThk2RpALASyXVQ",
            BuildConfig.API_KEY
        )
    }

    suspend fun getPLaylistItem(playlistId: String) = getResult {
        apiService.getPlaylistItems(BuildConfig.API_KEY, "snippet,contentDetails", playlistId)
    }

    suspend fun getVideo(videoId: String) = getResult {
        apiService.getVideo(BuildConfig.API_KEY,"snippet,contentDetails",videoId)
    }
}

