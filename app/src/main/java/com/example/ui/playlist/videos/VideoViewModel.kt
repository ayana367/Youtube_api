package com.example.ui.playlist.videos

import androidx.lifecycle.LiveData
import com.example.App
import com.example.core.network.ext.result.Resource
import com.example.core.network.ext.ui.BaseViewModel
import com.example.data.entity.model.ItemsItem

class VideoViewModel : BaseViewModel() {

    fun getVideo(videoId : String) : LiveData<Resource<ItemsItem>>{
        return App.repository.getVideo(videoId)
    }
}