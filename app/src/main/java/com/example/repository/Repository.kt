package com.example.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.BuildConfig
import com.example.core.network.ext.result.ui.Resource
import com.example.core.network.ext.result.ui.RetrofitClient
import com.example.data.local.entity.remote.ApiService
import com.example.data.local.entity.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): MutableLiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading()

        apiService.getPlaylists(
            "snippet,contentDetails", "UC3IZKseVpdzPSBaWxBxundA", BuildConfig.API_KEY
        ).enqueue(object : Callback<Playlists> {
            override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                if (response.isSuccessful){
                    data.postValue(Resource.success(response.body()))
                }
            }
            override fun onFailure(call: Call<Playlists>, t: Throwable) {
                data.value = Resource.error(t.message.toString(),null,null)
                Log.e("ololo", "${t.message}")
            }
        })
        return data
    }

    fun getItemList(playlistId: String): MutableLiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading()

        apiService.getPlaylistItems(BuildConfig.API_KEY,"snippet,contentDetails",playlistId)
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(
                    call: Call<Playlists>, response: Response<Playlists>
                ) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }
                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    data.value = Resource.error(t.stackTrace.toString(),null,null)
                    Log.e("ololo", "${t.stackTrace}")
                }
            })
        return data
    }
}