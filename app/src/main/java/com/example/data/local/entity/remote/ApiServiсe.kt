package com.example.data.local.entity.remote

import com.example.data.local.entity.remote.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") apiKey:String,
        @Query("channelId") channelId:String,
        @Query("key") key:String,
        @Query("maxResults") maxResults : Int = 20
    ): Call<Playlists>

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("playlistId") channelId : String,
        @Query("maxResults") maxResults : Int = 20
    ) : Call<Playlists>

}

