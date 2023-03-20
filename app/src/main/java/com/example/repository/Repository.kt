package com.example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.core.network.ext.result.Resource
import com.example.data.remote.RemoteDataSource
import com.example.data.entity.model.ItemsItem
import kotlinx.coroutines.Dispatchers


class Repository {
    private val remoteDataSource: RemoteDataSource by lazy {
        RemoteDataSource()
    }

    fun getPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getPlaylist()
        emit(response)

    }


    fun getItemList(playlistId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getPLaylistItem(playlistId)
        emit(response)
    }

    fun getVideo(videoId:String) : LiveData<Resource<ItemsItem>> =  liveData(Dispatchers.IO){
        emit(Resource.loading())
        val response = remoteDataSource.getVideo(videoId)
        emit(response)
    }
}